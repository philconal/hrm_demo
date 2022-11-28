package conal.hrm_demo.controller;


import conal.hrm_demo.dto.request.CreateSalaryRequest;
import conal.hrm_demo.dto.request.SalaryFilterRequest;
import conal.hrm_demo.dto.request.UpdateSalaryRequest;
import conal.hrm_demo.dto.response.ApplicationDataResponse;
import conal.hrm_demo.entity.Salary;
import conal.hrm_demo.services.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SalaryController {
    @Autowired
    private SalaryService salaryService;

    @RequestMapping(value = "salary", method = RequestMethod.GET)
    public ApplicationDataResponse<List<Salary>> getAllSalaries() {
        return new ApplicationDataResponse<>(HttpStatus.OK, new ArrayList<>());
    }

    @RequestMapping(value = "salary", method = RequestMethod.POST)
    public ApplicationDataResponse<Salary> addSalary(@RequestBody CreateSalaryRequest request) {
        Salary salary = salaryService.addSalary(request);
        // map to response
        return new ApplicationDataResponse<>(HttpStatus.OK, salary);
    }

    @RequestMapping(value = "/salary/paging", method = RequestMethod.GET)
    public ApplicationDataResponse<Page<Salary>> getAllEmployees(
            @RequestParam(value = "departmentId") Long id,
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") int size
    ) {
        SalaryFilterRequest filterRequest = new SalaryFilterRequest(
                id, page, size
        );
        return new ApplicationDataResponse<>(HttpStatus.OK, salaryService.getAllSalaryByEmployeeId(filterRequest));
    }

    @RequestMapping(value = "salary/{id}", method = RequestMethod.PUT)
    public ApplicationDataResponse<Salary> updateSalary(
            @PathVariable("id") Long id, @RequestBody UpdateSalaryRequest request
    ) {
        Salary salary = salaryService.updateSalary(request);
        return new ApplicationDataResponse<>(HttpStatus.OK, salary);
    }
}
