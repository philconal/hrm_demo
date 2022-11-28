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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
        return new ApplicationDataResponse<>(HttpStatus.OK, departmentService.getAllDepartments());
    }

    @RequestMapping(value = "/departments/paging", method = RequestMethod.GET)
    public ApplicationDataResponse<CustomPage<Department>> getAllDepartments(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "address", required = false) String address,
            @RequestParam(value = "code", required = false) String code,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "direction",  defaultValue = "UNSORTED") Direction direction,
            @RequestParam(value = "sort_field", required = false, defaultValue = "") String sortField
    ) {
        DepartmentFilterRequest filterRequest = new DepartmentFilterRequest(name, page, size, direction.getDirection(), sortField, address, code);
        return new ApplicationDataResponse<>(HttpStatus.OK, departmentService.getAllDepartmentsWithPaging(filterRequest));
    }

    @RequestMapping(value = "/departments/{id}", method = RequestMethod.GET)
    public ApplicationDataResponse<Department> getDepartmentById(@PathVariable("id") Long id) {
        return new ApplicationDataResponse<>(HttpStatus.OK, departmentService.getDepartmentByID(id));
    }

    @RequestMapping(value = "/departments", method = RequestMethod.POST)
    public ApplicationDataResponse<Department> addDepartment(@Valid @RequestBody CreateDepartmentRequest request) {
        Department department = Mapper.map(request);
        return new ApplicationDataResponse<>(HttpStatus.OK, departmentService.createDepartment(department));
    }

    @RequestMapping(value = "/departments/{id}", method = RequestMethod.PUT)
    public ApplicationDataResponse<Department> updateDepartment(@PathVariable("id") Long id, @Valid @RequestBody UpdateDepartmentRequest request) {
        if (request.getCode() != null && departmentService.isDepartmentCodeExisted(request.getCode()))
            throw new ApplicationException(HttpStatus.BAD_REQUEST, "Department Code is already exist");
        Department department = departmentService.getDepartmentByID(id);
        Department convert = Mapper.map(department, request);
        return new ApplicationDataResponse<>(HttpStatus.OK, departmentService.updateDepartment(convert));
    }

    @RequestMapping(value = "/departments/moveEmployee", method = RequestMethod.PUT)
    public ApplicationDataResponse<String> updateDepartment( @Valid @RequestBody MoveEmployeeRequest request) {
        departmentService.moveEmployeesToOtherDepartment(request);
        return new ApplicationDataResponse<>(HttpStatus.OK, "Moved employee successfully!");
    }

    @RequestMapping(value = "/departments/{id}", method = RequestMethod.DELETE)
    public ApplicationDataResponse<String> deleteDepartment(@PathVariable("id") Long id) {
        departmentService.deleteDepartment(id);
        return new ApplicationDataResponse<>(HttpStatus.OK, "Delete Department successfully!!");
    }
}
