package conal.hrm_demo.services;


import conal.hrm_demo.controller.helper.CSVHelper;
import conal.hrm_demo.controller.helper.Mapper;
import conal.hrm_demo.dto.request.CSVFilterRequest;
import conal.hrm_demo.dto.request.CreateSalaryRequest;
import conal.hrm_demo.dto.request.SalaryFilterRequest;
import conal.hrm_demo.dto.request.UpdateSalaryRequest;
import conal.hrm_demo.entity.Employee;
import conal.hrm_demo.entity.Salary;
import conal.hrm_demo.repository.SalaryRepository;
import conal.hrm_demo.repository.specification.SalarySpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Optional;

@Service
public class SalaryServiceImpl implements SalaryService {
    @Autowired
    private SalaryRepository salaryRepository;
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private SalarySpecification salarySpecification;

    public Salary addSalary(CreateSalaryRequest request) {
        Employee employee = employeeService.getEmployeeByID(request.getEmployee_id());
        return salaryRepository.save(Mapper.map(employee, request));
    }

    @Override
    public void saveSalary(Salary request) {
        salaryRepository.save(request);
    }

    public Salary updateSalary(UpdateSalaryRequest request) {
        Salary salary = this.findSalaryById(request.getId());
        Employee employee = employeeService.getEmployeeByID(request.getEmployee_id());
        return salaryRepository.save(Mapper.map(salary, employee, request));
    }

    @Override
    public ByteArrayInputStream downloadSalaryFile(CSVFilterRequest request) {
        return CSVHelper.salariesToCSV(this.getAllSalaries(request));
    }

    public Salary findSalaryById(Long id) {
        Optional<Salary> salary = salaryRepository.findByOne(id);
        salary.orElseThrow(() -> {
            throw new IllegalArgumentException("Salary not found!");
        });
        return salary.get();
    }

    @Override
    public Page<Salary> getAllSalaryByEmployeeId(SalaryFilterRequest request) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        return salaryRepository.findAllByEmployeeId(pageable, request.getDepartmentId());
    }

    @Override
    public List<Salary> getAllSalariesOfAnEmployee(Long employeeId) {

        return null;
    }

    @Override
    public List<Salary> getAllSalaries(CSVFilterRequest request) {
        return salaryRepository.findAll(salarySpecification.doFilter(request.getFromDate(), request.getToDate(), request.getEmployeeId(), request.getDepartmentId()));
    }

    @Override
    public Salary getSalaryOfEachEmployeeByMonth(Long id, int month) {
        return null;
    }

    @Override
    public List<Salary> getSalaryOfEachEmployee(Long id, SalaryFilterRequest request) {
        return null;
    }

    @Override
    public List<Salary> getSalaryOfAllEmployee(SalaryFilterRequest request) {
        return null;
    }

}
