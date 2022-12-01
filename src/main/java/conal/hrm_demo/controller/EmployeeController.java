package conal.hrm_demo.controller;

import conal.hrm_demo.controller.helper.Mapper;
import conal.hrm_demo.dto.request.CreateEmployeeRequest;
import conal.hrm_demo.dto.request.EmployeeFilterRequest;
import conal.hrm_demo.dto.request.UpdateEmployeeRequest;
import conal.hrm_demo.dto.response.ApplicationDataResponse;
import conal.hrm_demo.dto.response.CustomPage;
import conal.hrm_demo.entity.Department;
import conal.hrm_demo.entity.Employee;
import conal.hrm_demo.entity.enums.Direction;
import conal.hrm_demo.services.DepartmentService;
import conal.hrm_demo.services.EmployeeService;
import conal.hrm_demo.util.Generate;
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
        return ApplicationDataResponse.<List<Employee>>builder()
                .status(HttpStatus.OK)
                .data(employeeService.getAllEmployees())
                .build();
    }

    @RequestMapping(value = "/employees/paging", method = RequestMethod.GET)
    public ApplicationDataResponse<CustomPage<Employee>> getAllEmployees(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "direction", defaultValue = "UNSORTED") Direction direction,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "address", required = false) String address,
            @RequestParam(value = "code", required = false) String code,
            @RequestParam(value = "sortField", required = false, defaultValue = "") String sortField,
            @RequestParam(value = "phone", required = false) String phone,
            @RequestParam(value = "email", required = false) String email
//            @RequestParam(value = "departmentCode", required = false) String departmentCode,
//            @RequestParam(value = "departmentName", required = false) String departmentName,
//            @RequestParam(value = "startedFrom", required = false) String startedFrom,
//            @RequestParam(value = "startedTo", required = false) String startedTo,
//            @RequestParam(value = "endedFrom", required = false) String endedFrom,
//            @RequestParam(value = "endedTo", required = false) String endedTo,
//            @RequestParam(value = "salaryFrom", required = false) String salaryFrom,
//            @RequestParam(value = "salaryTo", required = false) String salaryTo
    ) {
        EmployeeFilterRequest filterRequest =
                EmployeeFilterRequest
                        .builder()
                        .name(name)
                        .phone(phone)
                        .code(code)
                        .email(email)
                        .address(address)
                        .page(page)
                        .size(size)
                        .direction(direction.getDirection())
                        .sortField(sortField)
                        .build();
        return ApplicationDataResponse.<CustomPage<Employee>>builder()
                .status(HttpStatus.OK)
                .data(employeeService.getAllEmployeesWithPaging(filterRequest))
                .build();
    }

    @RequestMapping(value = "/employees/department/{id}", method = RequestMethod.GET)
    public ApplicationDataResponse<CustomPage<Employee>> getAllEmployees(
            @PathVariable("id") Long id,
            @RequestParam(value = "sort", required = false, defaultValue = "false") boolean sort,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sortField", required = false, defaultValue = "") String sortField

    ) {
        return ApplicationDataResponse.<CustomPage<Employee>>builder()
                .status(HttpStatus.OK)
                .data(employeeService.getAllEmployeesByDepartmentId(id, page, size, sort, sortField))
                .build();
    }

    @RequestMapping(value = "/employees/{id}", method = RequestMethod.GET)
    public ApplicationDataResponse<Employee> getEmployeeById(@PathVariable("id") Long id) {
        return ApplicationDataResponse.<Employee>builder()
                .status(HttpStatus.OK)
                .data(employeeService.getEmployeeByID(id))
                .build();
    }

//    @RequestMapping(value = "/employees/nth", method = RequestMethod.GET)
//    public ApplicationDataResponse<Employee> getNthPaid() {
//        return new ApplicationDataResponse<>(HttpStatus.OK, employeeService.getNthPaidEmployee(1));
//    }

    @RequestMapping(value = "/employees", method = RequestMethod.POST)
    public ApplicationDataResponse<Employee> addEmployee(@Valid @RequestBody CreateEmployeeRequest request) {
        Validator.checkPhoneFormat(request.getPhone());
        Validator.validateEmail(request.getEmail());
        if (request.getCode() == null) {
            throw Generate.throwNotFoundExceptionMessage("Employee Code should not be blank!");
        }
        Employee employee = Mapper.map(request);
        Department department = departmentService.getDepartmentByID(request.getDepartmentId());
        employee.setDepartment(department);
        return ApplicationDataResponse.<Employee>builder()
                .status(HttpStatus.OK)
                .data(employeeService.addEmployee(employee))
                .build();
    }

    @RequestMapping(value = "/employees/{id}", method = RequestMethod.PUT)
    public ApplicationDataResponse<Employee> updateEmployee(@PathVariable("id") Long id, @Valid @RequestBody UpdateEmployeeRequest request) {
        Employee employeeByID = employeeService.getEmployeeByID(id);

        Employee employee = Mapper.map(employeeByID, request);

        if (request.getDepartmentId() != null && !request.getDepartmentId().trim().isBlank()) {
            Department department = departmentService.getDepartmentByID(Long.parseLong(request.getDepartmentId()));
            employee.setDepartment(department);
        }
        return ApplicationDataResponse.<Employee>builder()
                .status(HttpStatus.OK)
                .data(employeeService.updateEmployee(employee))
                .build();
    }

    @RequestMapping(value = "/employees/{id}", method = RequestMethod.DELETE)
    public ApplicationDataResponse<String> deleteEmployee(@PathVariable("id") Long id) {
        employeeService.deleteEmployee(id);
        return ApplicationDataResponse.<String>builder()
                .status(HttpStatus.OK)
                .data("Delete Employee successfully")
                .build();
    }


}
