package conal.hrm_demo.services;

import conal.hrm_demo.dto.request.CreateSalaryRequest;
import conal.hrm_demo.dto.request.SalaryFilterRequest;
import conal.hrm_demo.dto.request.UpdateSalaryRequest;
import conal.hrm_demo.entity.Salary;

import java.util.List;

public interface SalaryService {
    Salary addSalary(CreateSalaryRequest request);

    Salary updateSalary(UpdateSalaryRequest request);

    Salary findSalaryById(Long id);

    List<Salary> getAllSalariesOfAnEmployee(Long employeeId);

    Salary getSalaryOfEachEmployeeByMonth(Long id, int month);

    List<Salary> getSalaryOfEachEmployee(Long id, SalaryFilterRequest request);

    List<Salary> getSalaryOfAllEmployee(SalaryFilterRequest request);


}
