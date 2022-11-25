package conal.hrm_demo.services;

import conal.hrm_demo.dto.request.CreateSalaryRequest;
import conal.hrm_demo.dto.request.UpdateSalaryRequest;
import conal.hrm_demo.entity.Salary;

public interface SalaryService {
    Salary addSalary(CreateSalaryRequest request);

    Salary updateSalary(UpdateSalaryRequest request);

    Salary findSalaryById(Long id);

}
