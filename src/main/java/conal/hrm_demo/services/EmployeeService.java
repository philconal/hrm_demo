package conal.hrm_demo.services;

import conal.hrm_demo.dto.request.EmployeeFilterRequest;
import conal.hrm_demo.dto.response.CustomPage;
import conal.hrm_demo.entity.Employee;

import java.util.List;

public interface EmployeeService {
    Employee saveEmployee(Employee employee);

    List<Employee> getAllEmployees(EmployeeFilterRequest filterRequest);

    Employee deleteEmployee(Long id);

    Employee getEmployeeByID(Long id);

    Employee addEmployee(Employee employee);

    Employee updateEmployee(Employee employee);

    CustomPage<Employee> getAllEmployeesWithPaging(EmployeeFilterRequest filterRequest);

    CustomPage<Employee> getAllEmployeesByDepartmentId(Long departmentId, int page,
                                                       int size,
                                                       boolean sort,
                                                       String sortField);
    List<Employee> getAllEmployeesByDepartmentId(Long departmentId);

    Employee getNthPaidEmployee(int position);



}
