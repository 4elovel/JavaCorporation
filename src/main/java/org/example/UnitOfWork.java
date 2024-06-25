package org.example;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class UnitOfWork {

    private static EmployeeRepository repository;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the filename to load employees from: ");
        String filename = scanner.nextLine();

        repository = new EmployeeRepositoryImpl(filename);
        repository.loadFromFile(filename);

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Add employee");
            System.out.println("2. Update employee");
            System.out.println("3. Delete employee");
            System.out.println("4. Find employee by last name");
            System.out.println("5. Display all employees");
            System.out.println("6. Display employees by last name starting with letter");
            System.out.println("7. Save employees to file");
            System.out.println("8. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addEmployee(scanner);
                    break;
                case 2:
                    updateEmployee(scanner);
                    break;
                case 3:
                    deleteEmployee(scanner);
                    break;
                case 4:
                    findEmployee(scanner);
                    break;
                case 5:
                    displayAllEmployees();
                    break;
                case 6:
                    displayEmployeesByLastNameStartingWith(scanner);
                    break;
                case 7:
                    repository.saveToFile();
                    System.out.println("Employees saved");
                    break;
                case 8:
                    repository.saveToFile();
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addEmployee(Scanner scanner) {
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter birth date (yyyy-mm-dd): ");
        LocalDate birthDate = LocalDate.parse(scanner.nextLine());
        System.out.print("Enter position: ");
        String position = scanner.nextLine();

        Employee employee = new Employee(firstName, lastName, birthDate, position);
        repository.addEmployee(employee);
        System.out.println("Employee added successfully.");
    }

    private static void updateEmployee(Scanner scanner) {
        System.out.print("Enter the last name of the employee to update: ");
        String lastName = scanner.nextLine();
        Optional<Employee> optionalEmployee = repository.findEmployeeByLastName(lastName);

        if (optionalEmployee.isPresent()) {
            System.out.print("Enter new first name: ");
            String firstName = scanner.nextLine();
            System.out.print("Enter new last name: ");
            String newLastName = scanner.nextLine();
            System.out.print("Enter new birth date (yyyy-mm-dd): ");
            LocalDate birthDate = LocalDate.parse(scanner.nextLine());
            System.out.print("Enter new position: ");
            String position = scanner.nextLine();

            Employee updatedEmployee = new Employee(firstName, newLastName, birthDate, position);
            repository.updateEmployee(lastName, updatedEmployee);
            System.out.println("Employee updated successfully.");
        } else {
            System.out.println("Employee not found.");
        }
    }

    private static void deleteEmployee(Scanner scanner) {
        System.out.print("Enter the last name of the employee to delete: ");
        String lastName = scanner.nextLine();
        repository.deleteEmployee(lastName);
        System.out.println("Employee deleted successfully.");
    }

    private static void findEmployee(Scanner scanner) {
        System.out.print("Enter the last name of the employee to find: ");
        String lastName = scanner.nextLine();
        Optional<Employee> optionalEmployee = repository.findEmployeeByLastName(lastName);

        if (optionalEmployee.isPresent()) {
            System.out.println(optionalEmployee.get());
        } else {
            System.out.println("Employee not found.");
        }
    }

    private static void displayAllEmployees() {
        List<Employee> employees = repository.findAllEmployees();
        employees.forEach(System.out::println);
    }

    private static void displayEmployeesByLastNameStartingWith(Scanner scanner) {
        System.out.print("Enter the starting letter of last names: ");
        char letter = scanner.nextLine().charAt(0);
        List<Employee> employees = repository.findEmployeesByLastNameStartsWith(letter);
        employees.forEach(System.out::println);
    }
}
