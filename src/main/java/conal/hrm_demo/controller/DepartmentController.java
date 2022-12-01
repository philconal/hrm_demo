package conal.hrm_demo.controller;

import conal.hrm_demo.controller.helper.Mapper;
import conal.hrm_demo.dto.request.CreateDepartmentRequest;
import conal.hrm_demo.dto.request.DepartmentFilterRequest;
import conal.hrm_demo.dto.request.MoveEmployeeRequest;
import conal.hrm_demo.dto.request.UpdateDepartmentRequest;
import conal.hrm_demo.dto.response.ApplicationDataResponse;
import conal.hrm_demo.dto.response.CustomPage;
import conal.hrm_demo.entity.Department;
import conal.hrm_demo.entity.enums.Direction;
import conal.hrm_demo.exception.ApplicationException;
import conal.hrm_demo.services.DepartmentService;
import conal.hrm_demo.util.Generate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @RequestMapping(value = "/departments", method = RequestMethod.GET)
    public ApplicationDataResponse<List<Department>> getAllDepartments() {
        return ApplicationDataResponse.<List<Department>>builder()
                .status(HttpStatus.OK)
                .data(departmentService.getAllDepartments())
                .build();
    }

    @RequestMapping(value = "/departments/paging", method = RequestMethod.GET)
    public ApplicationDataResponse<CustomPage<Department>> getAllDepartments(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "address", required = false) String address,
            @RequestParam(value = "code", required = false) String code,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "direction", defaultValue = "UNSORTED") Direction direction,
            @RequestParam(value = "sort_field", required = false, defaultValue = "") String sortField
    ) {
        DepartmentFilterRequest filterRequest = new DepartmentFilterRequest(name, page, size, direction.getDirection(), sortField, address, code);
        return ApplicationDataResponse.<CustomPage<Department>>builder()
                .status(HttpStatus.OK)
                .data(departmentService.getAllDepartmentsWithPaging(filterRequest))
                .build();
    }

    @RequestMapping(value = "/departments/{id}", method = RequestMethod.GET)
    public ApplicationDataResponse<Department> getDepartmentById(@PathVariable("id") Long id) {
        return ApplicationDataResponse.<Department>builder()
                .status(HttpStatus.OK)
                .data(departmentService.getDepartmentByID(id))
                .build();
    }

    @RequestMapping(value = "/departments", method = RequestMethod.POST)
    public ApplicationDataResponse<Department> addDepartment(@Valid @RequestBody CreateDepartmentRequest request) {
        Department department = Mapper.map(request);
        return ApplicationDataResponse.<Department>builder()
                .status(HttpStatus.OK)
                .data(departmentService.createDepartment(department))
                .build();
    }

    @RequestMapping(value = "/departments/{id}", method = RequestMethod.PUT)
    public ApplicationDataResponse<Department> updateDepartment(@PathVariable("id") Long id, @Valid @RequestBody UpdateDepartmentRequest request) {
        if (request.getCode() != null && departmentService.isDepartmentCodeExisted(request.getCode()))
            throw Generate.throwNotFoundExceptionMessage( "Department Code is already exist");
        Department department = departmentService.getDepartmentByID(id);
        Department convert = Mapper.map(department, request);
        return ApplicationDataResponse.<Department>builder()
                .status(HttpStatus.OK)
                .data(departmentService.updateDepartment(convert))
                .build();
    }

    @RequestMapping(value = "/departments/moveEmployee", method = RequestMethod.PUT)
    public ApplicationDataResponse<String> updateDepartment(@Valid @RequestBody MoveEmployeeRequest request) {
        departmentService.moveEmployeesToOtherDepartment(request);
        return ApplicationDataResponse.<String>builder()
                .status(HttpStatus.OK)
                .data("Moved employee successfully!")
                .build();
    }

    @RequestMapping(value = "/departments/{id}", method = RequestMethod.DELETE)
    public ApplicationDataResponse<String> deleteDepartment(@PathVariable("id") Long id) {
        departmentService.deleteDepartment(id);
        return ApplicationDataResponse.<String>builder()
                .status(HttpStatus.OK)
                .data("Delete Department successfully!!")
                .build();
    }
}
