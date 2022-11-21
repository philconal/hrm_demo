package conal.hrm_demo.controller;

import conal.hrm_demo.controller.helper.Mapper;
import conal.hrm_demo.dto.request.CreateEmployeeRequest;
import conal.hrm_demo.dto.request.UpdateEmployeeRequest;
import conal.hrm_demo.dto.response.ApplicationDataResponse;
import conal.hrm_demo.entity.Department;
import conal.hrm_demo.entity.Employee;
import conal.hrm_demo.services.DepartmentService;
import conal.hrm_demo.services.EmployeeService;
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
        return new ApplicationDataResponse<>(HttpStatus.OK, employeeService.getAllEmployees());
    }

    @RequestMapping(value = "/employees/{id}", method = RequestMethod.GET)
    public ApplicationDataResponse<Employee> getEmployeeById(@PathVariable("id") Long id) {
        return new ApplicationDataResponse<>(HttpStatus.OK, employeeService.getEmployeeByID(id));
    }

    @RequestMapping(value = "/employees", method = RequestMethod.POST)
    public ApplicationDataResponse<Employee> addEmployee(@Valid @RequestBody CreateEmployeeRequest request) {
        Employee employee = Mapper.map(request);
        Department department = departmentService.getDepartmentByID(request.getDepartmentId());
        System.out.println(department.getId());
        employee.setDepartment(department);
        return new ApplicationDataResponse<>(HttpStatus.OK, employeeService.addEmployee(employee));
    }

    @RequestMapping(value = "/employees/{id}", method = RequestMethod.PUT)
    public ApplicationDataResponse<Employee> updateEmployee(@PathVariable("id") Long id, @Valid @RequestBody UpdateEmployeeRequest request) {
        Employee employeeByID = employeeService.getEmployeeByID(id);
        Employee employee = Mapper.map(employeeByID, request);
        Department department = departmentService.getDepartmentByID(request.getDepartmentId());
        employee.setDepartment(department);
        return new ApplicationDataResponse<>(HttpStatus.OK, employeeService.updateEmployee(employee));
    }

    @RequestMapping(value = "/employees/{id}", method = RequestMethod.DELETE)
    public ApplicationDataResponse<Employee> deleteEmployee(@PathVariable("id") Long id) {
        return new ApplicationDataResponse<>(HttpStatus.OK, employeeService.deleteEmployee(id));
    }


}
