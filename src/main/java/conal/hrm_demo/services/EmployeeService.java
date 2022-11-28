package conal.hrm_demo.services;

import conal.hrm_demo.dto.request.EmployeeFilterRequest;
import conal.hrm_demo.entity.Employee;
import conal.hrm_demo.entity.enums.Order;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EmployeeService {
    Employee saveEmployee(Employee employee);

    List<Employee> getAllEmployees(EmployeeFilterRequest filterRequest);

    Employee deleteEmployee(Long id);

    Employee getEmployeeByID(Long id);

    Employee addEmployee(Employee employee);

    Employee updateEmployee(Employee employee);

    Page<Employee> getAllEmployeesWithPaging(EmployeeFilterRequest filterRequest);
    Page<Employee> getAllEmployeesByDepartmentId(Long departmentId, int page,
    int size,
    boolean sort,
    String sortField);

    Employee getNthPaidEmployee(int position);

    List<Employee> getEmployeeWithOrderedSalary(Order order);



}
