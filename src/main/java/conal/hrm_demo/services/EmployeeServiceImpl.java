package conal.hrm_demo.services;

import conal.hrm_demo.entity.Employee;
import conal.hrm_demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee deleteEmployee(Long id) {
        Employee employeeByID = this.getEmployeeByID(id);
        employeeByID.setActive(false);
        return employeeRepository.save(employeeByID);
    }

    @Override
    public Employee getEmployeeByID(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isEmpty()) {
            throw new IllegalArgumentException("The employee with id: " + id + " not found");
        }
        return employee.get();
    }

    @Override
    public Employee addEmployee(Employee employee) {
        return this.saveEmployee(employee);
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        return this.saveEmployee(employee);
    }
}
