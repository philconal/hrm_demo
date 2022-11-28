package conal.hrm_demo.services;

import conal.hrm_demo.dto.request.EmployeeFilterRequest;
import conal.hrm_demo.entity.Department;
import conal.hrm_demo.entity.Employee;
import conal.hrm_demo.entity.enums.Order;
import conal.hrm_demo.exception.ApplicationException;
import conal.hrm_demo.repository.EmployeeRepository;
import conal.hrm_demo.repository.specification.EmployeeSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeSpecification employeeSpecification;

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees(EmployeeFilterRequest filterRequest) {
        return employeeRepository.findAll();
    }


    @Override
    public Employee deleteEmployee(Long id) {
        Employee employeeByID = this.getEmployeeByID(id);
        employeeByID.setActive(false);
        return employeeRepository.save(employeeByID);
    }

    @Override
    public Employee getEmployeeByID(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        return employee.orElseThrow(() -> {
            throw new ApplicationException(HttpStatus.BAD_REQUEST, "Employee is not found!!");
        });
    }

    @Override
    public Employee addEmployee(Employee employee) {
        if (employeeRepository.existsByCode(employee.getCode()))
            throw new ApplicationException(HttpStatus.BAD_REQUEST, "Employee Code is already exist");
        if (employeeRepository.existsByEmail(employee.getEmail()))
            throw new ApplicationException(HttpStatus.BAD_REQUEST, "Employee email is already exist");
        if (employeeRepository.existsByPhone(employee.getPhone()))
            throw new ApplicationException(HttpStatus.BAD_REQUEST, "Employee phone is already exist");
        return this.saveEmployee(employee);
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        return this.saveEmployee(employee);
    }

    @Override
    public Page<Employee> getAllEmployeesWithPaging(EmployeeFilterRequest filterRequest) {
        Specification<Employee> specification = employeeSpecification.doFilter(
                filterRequest.getName(),
                filterRequest.isSort(),
                filterRequest.getSortField(),
                filterRequest.getAddress(),
                filterRequest.getCode(),
                filterRequest.getPhone(),
                filterRequest.getEmail(),
                filterRequest.getDepartmentCode(),
                filterRequest.getDepartmentName(),
                filterRequest.getStartedFrom(),
                filterRequest.getStartedTo(),
                filterRequest.getEndedFrom(),
                filterRequest.getEndedTo(),
                filterRequest.getSalaryFrom(),
                filterRequest.getSalaryTo()
        );
        Pageable pageable = PageRequest.of(filterRequest.getPage() - 1, filterRequest.getSize());
        return employeeRepository.findAll(specification, pageable);
    }


    @Override
    public Employee getNthPaidEmployee(int position) {
        final var maps = findTotalPaymentOfEachEmployee(this.getAllEmployees(null));
        for (Map.Entry<Double, Long> map : maps.entrySet()
        ) {
            System.out.println(map);
        }
        final var collect = maps.keySet().stream().map(Double::intValue).collect(Collectors.toSet());
        return null;
    }

    private Map<Double, Long> findTotalPaymentOfEachEmployee(List<Employee> employees) {
        Map<Double, Long> list = new LinkedHashMap<>();
        employees.forEach(item -> {
            Double total = item.getSalaries().stream().map(salary ->
                    (salary.getAmount() + salary.getBonus())
            ).max((a, b) -> (int) (a - b)).orElse(0.0);
            list.put(total, item.getId());
        });
        return list;
    }

    @Override
    public List<Employee> getEmployeeWithOrderedSalary(Order order) {
        return null;
    }
}
