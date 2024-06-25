package org.example;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeRepositoryImpl implements EmployeeRepository {

    private final String filename;
    private List<Employee> employees = new ArrayList<>();

    public EmployeeRepositoryImpl(String filename) {
        this.filename = filename;
    }

    @Override
    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    @Override
    public void updateEmployee(String lastName, Employee updatedEmployee) {
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getLastName().equals(lastName)) {
                employees.set(i, updatedEmployee);
                return;
            }
        }
    }

    @Override
    public void deleteEmployee(String lastName) {
        employees.removeIf(employee -> employee.getLastName().equals(lastName));
    }

    @Override
    public Optional<Employee> findEmployeeByLastName(String lastName) {
        return employees.stream().filter(employee -> employee.getLastName().equals(lastName))
                .findFirst();
    }

    @Override
    public ArrayList<Employee> findAllEmployees() {
        return new ArrayList<>(employees);
    }

    @Override
    public ArrayList<Employee> findEmployeesByLastNameStartsWith(char letter) {
        return new ArrayList<>(
                employees.stream().filter(e -> e.getLastName().startsWith(String.valueOf(letter)))
                        .toList());
    }

    @Override
    public void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(employees);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void loadFromFile(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            employees = (List<Employee>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
