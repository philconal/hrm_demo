package conal.hrm_demo.services;


import conal.hrm_demo.controller.helper.Mapper;
import conal.hrm_demo.dto.request.CreateSalaryRequest;
import conal.hrm_demo.dto.request.UpdateSalaryRequest;
import conal.hrm_demo.entity.Employee;
import conal.hrm_demo.entity.Salary;
import conal.hrm_demo.repository.SalaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SalaryServiceImpl implements SalaryService {
    @Autowired
    private SalaryRepository salaryRepository;
    @Autowired
    private EmployeeService employeeService;

    public Salary addSalary(CreateSalaryRequest request) {
        Employee employee = employeeService.getEmployeeByID(request.getEmployee_id());
        return salaryRepository.save(Mapper.map(employee, request));
    }

    public Salary updateSalary(UpdateSalaryRequest request) {
        Salary salary = this.findSalaryById(request.getId());
        Employee employee = employeeService.getEmployeeByID(request.getEmployee_id());
        return salaryRepository.save(Mapper.map(salary, employee, request));
    }

    public Salary findSalaryById(Long id) {
        Optional<Salary> salary = salaryRepository.findByOne(id);
        salary.orElseThrow(() -> {
            throw new IllegalArgumentException("Salary not found!");
        });
        return salary.get();
    }

}
