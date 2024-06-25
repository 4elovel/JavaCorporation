package org.example;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository {

    void addEmployee(Employee employee);

    void updateEmployee(String lastName, Employee updatedEmployee);

    void deleteEmployee(String lastName);

    Optional<Employee> findEmployeeByLastName(String lastName);

    List<Employee> findAllEmployees();

    List<Employee> findEmployeesByLastNameStartsWith(char letter);

    void saveToFile();

    void loadFromFile(String filename);
}
