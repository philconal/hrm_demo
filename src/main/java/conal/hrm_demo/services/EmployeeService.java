package conal.hrm_demo.services;

import conal.hrm_demo.entity.Employee;

import java.util.List;

public interface EmployeeService {
    Employee saveEmployee(Employee employee);

    List<Employee> getAllEmployees();

    Employee deleteEmployee(Long id);

    Employee getEmployeeByID(Long id);
    Employee addEmployee(Employee employee);
    Employee updateEmployee(Employee employee);
}
