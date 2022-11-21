package conal.hrm_demo.controller;

import conal.hrm_demo.controller.helper.Mapper;
import conal.hrm_demo.dto.request.CreateDepartmentRequest;
import conal.hrm_demo.dto.request.UpdateDepartmentRequest;
import conal.hrm_demo.dto.response.ApplicationDataResponse;
import conal.hrm_demo.entity.Department;
import conal.hrm_demo.services.DepartmentService;
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
        return new ApplicationDataResponse<>(HttpStatus.OK, departmentService.getAllDepartments());
    }

    @RequestMapping(value = "/departments/{id}", method = RequestMethod.GET)
    public ApplicationDataResponse<Department> getDepartmentById(@PathVariable("id") Long id) {
        return new ApplicationDataResponse<>(HttpStatus.OK, departmentService.getDepartmentByID(id));
    }

    @RequestMapping(value = "/departments", method = RequestMethod.POST)
    public ApplicationDataResponse<Department> addDepartment(@Valid  @RequestBody CreateDepartmentRequest request) {
        Department department = Mapper.map(request);
        return new ApplicationDataResponse<>(HttpStatus.OK, departmentService.saveDepartment(department));
    }

    @RequestMapping(value = "/departments/{id}", method = RequestMethod.PUT)
    public ApplicationDataResponse<Department> updateDepartment(@PathVariable("id") Long id, @Valid @RequestBody UpdateDepartmentRequest request) {
        Department department = departmentService.getDepartmentByID(id);
        Department convert = Mapper.map(department, request);
        return new ApplicationDataResponse<>(HttpStatus.OK, departmentService.saveDepartment(convert));
    }

    @RequestMapping(value = "/departments/{id}", method = RequestMethod.DELETE)
    public ApplicationDataResponse<String> deleteDepartment(@PathVariable("id") Long id) {
        return new ApplicationDataResponse<>(HttpStatus.OK, "Delete Department successfully!!");
    }
}
