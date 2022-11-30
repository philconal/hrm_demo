package conal.hrm_demo.services;

import conal.hrm_demo.dto.request.CSVFilterRequest;
import conal.hrm_demo.dto.request.CreateSalaryRequest;
import conal.hrm_demo.dto.request.SalaryFilterRequest;
import conal.hrm_demo.dto.request.UpdateSalaryRequest;
import conal.hrm_demo.entity.Salary;
import org.springframework.data.domain.Page;

import java.io.ByteArrayInputStream;
import java.util.List;

public interface SalaryService {
    Salary addSalary(CreateSalaryRequest request);

    void saveSalary(Salary request);

    Salary updateSalary(UpdateSalaryRequest request);

    ByteArrayInputStream downloadSalaryFile(CSVFilterRequest request);

    Salary findSalaryById(Long id);

    Page<Salary> getAllSalaryByEmployeeId(SalaryFilterRequest request);

    List<Salary> getAllSalariesOfAnEmployee(Long employeeId);
    List<Salary> getAllSalaries(CSVFilterRequest request);
    Salary getSalaryOfEachEmployeeByMonth(Long id, int month);

    List<Salary> getSalaryOfEachEmployee(Long id, SalaryFilterRequest request);

    List<Salary> getSalaryOfAllEmployee(SalaryFilterRequest request);


}
