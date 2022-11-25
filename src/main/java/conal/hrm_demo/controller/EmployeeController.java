package conal.hrm_demo.controller;

import conal.hrm_demo.controller.helper.Mapper;
import conal.hrm_demo.dto.request.CreateEmployeeRequest;
import conal.hrm_demo.dto.request.UpdateEmployeeRequest;
import conal.hrm_demo.dto.response.ApplicationDataResponse;
import conal.hrm_demo.entity.Department;
import conal.hrm_demo.entity.Employee;
import conal.hrm_demo.exception.ApplicationException;
import conal.hrm_demo.services.DepartmentService;
import conal.hrm_demo.services.EmployeeService;
import conal.hrm_demo.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class EmployeeController {
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "/employees", method = RequestMethod.GET)
    public ApplicationDataResponse<List<Employee>> getAllEmployees() {
        return new ApplicationDataResponse<>(HttpStatus.OK, employeeService.getAllEmployees(null));
    }

    @RequestMapping(value = "/employees/{id}", method = RequestMethod.GET)
    public ApplicationDataResponse<Employee> getEmployeeById(@PathVariable("id") Long id) {
        return new ApplicationDataResponse<>(HttpStatus.OK, employeeService.getEmployeeByID(id));
    }

    @RequestMapping(value = "/employees/nth", method = RequestMethod.GET)
    public ApplicationDataResponse<Employee> getNthPaid() {
        return new ApplicationDataResponse<>(HttpStatus.OK, employeeService.getNthPaidEmployee(1));
    }

    @RequestMapping(value = "/employees", method = RequestMethod.POST)
    public ApplicationDataResponse<Employee> addEmployee(@Valid @RequestBody CreateEmployeeRequest request) {
        Validator.checkPhoneFormat(request.getPhone());
        Validator.validateEmail(request.getEmail());
        if (request.getCode() == null) {
            throw new ApplicationException(HttpStatus.BAD_REQUEST, "Employee Code should not be blank!");
        }
        Employee employee = Mapper.map(request);
        Department department = departmentService.getDepartmentByID(request.getDepartmentId());
        employee.setDepartment(department);
        return new ApplicationDataResponse<>(HttpStatus.OK, employeeService.addEmployee(employee));
    }

    @RequestMapping(value = "/employees/{id}", method = RequestMethod.PUT)
    public ApplicationDataResponse<Employee> updateEmployee(@PathVariable("id") Long id, @Valid @RequestBody UpdateEmployeeRequest request) {
        Employee employeeByID = employeeService.getEmployeeByID(id);
        Employee employee = Mapper.map(employeeByID, request);
        System.out.println(request.getDepartmentId());

        if (request.getDepartmentId() != null) {
            System.out.println("inside if");
            Department department = departmentService.getDepartmentByID(request.getDepartmentId());
            employee.setDepartment(department);
        }
        System.out.println("Department: " + employee);
        return new ApplicationDataResponse<>(HttpStatus.OK, employeeService.updateEmployee(employee));
    }

    @RequestMapping(value = "/employees/{id}", method = RequestMethod.DELETE)
    public ApplicationDataResponse<Employee> deleteEmployee(@PathVariable("id") Long id) {
        return new ApplicationDataResponse<>(HttpStatus.OK, employeeService.deleteEmployee(id));
    }


}
