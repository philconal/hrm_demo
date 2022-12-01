package conal.hrm_demo.controller;


import conal.hrm_demo.dto.request.CSVFilterRequest;
import conal.hrm_demo.dto.request.CreateSalaryRequest;
import conal.hrm_demo.dto.request.SalaryFilterRequest;
import conal.hrm_demo.dto.request.UpdateSalaryRequest;
import conal.hrm_demo.dto.response.ApplicationDataResponse;
import conal.hrm_demo.dto.response.CustomPage;
import conal.hrm_demo.entity.Salary;
import conal.hrm_demo.services.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SalaryController {
    @Autowired
    private SalaryService salaryService;

    @RequestMapping(value = "salary", method = RequestMethod.GET)
    public ApplicationDataResponse<List<Salary>> getAllSalaries() {
        return ApplicationDataResponse.<List<Salary>>builder()
                .status(HttpStatus.OK)
                .data(new ArrayList<>())
                .build();
    }

    @RequestMapping(value = "salary", method = RequestMethod.POST)
    public ApplicationDataResponse<Salary> addSalary(@RequestBody CreateSalaryRequest request) {
        Salary salary = salaryService.addSalary(request);
        // map to response
        return ApplicationDataResponse.<Salary>builder()
                .status(HttpStatus.OK)
                .data(salary)
                .build();
    }

    @GetMapping("/download")
    public ResponseEntity<Resource> getFile(
            @RequestParam(value = "fromDate", required = false) String fromDate,
            @RequestParam(value = "toDate", required = false) String toDate,
            @RequestParam(value = "departmentId", required = false) String departmentId,
            @RequestParam(value = "employeeId", required = false) String employeeId
    ) {
        final var request =
                CSVFilterRequest
                        .builder()
                        .fromDate(fromDate)
                        .toDate(toDate)
                        .departmentId(departmentId)
                        .employeeId(employeeId)
                        .build();
        String filename = "salaries.csv";
        InputStreamResource file = new InputStreamResource(salaryService.downloadSalaryFile(request));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/csv"))
                .body(file);
    }

    @RequestMapping(value = "/salary/paging", method = RequestMethod.GET)
    public ApplicationDataResponse<CustomPage<Salary>> getAllEmployees(
            @RequestParam(value = "departmentId") Long id,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        SalaryFilterRequest filterRequest =
                SalaryFilterRequest
                        .builder()
                        .departmentId(id)
                        .size(size)
                        .page(page)
                        .build();
        return ApplicationDataResponse.<CustomPage<Salary>>builder()
                .status(HttpStatus.OK)
                .data(salaryService.getAllSalaryByEmployeeId(filterRequest))
                .build();
    }

    @RequestMapping(value = "salary/{id}", method = RequestMethod.PUT)
    public ApplicationDataResponse<Salary> updateSalary(
            @PathVariable("id") Long id, @RequestBody UpdateSalaryRequest request
    ) {
        Salary salary = salaryService.updateSalary(request);
        return ApplicationDataResponse.<Salary>builder()
                .status(HttpStatus.OK)
                .data(salary)
                .build();
    }
}
