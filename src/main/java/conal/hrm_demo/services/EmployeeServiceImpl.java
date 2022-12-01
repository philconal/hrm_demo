package conal.hrm_demo.services;

import conal.hrm_demo.dto.request.EmployeeFilterRequest;
import conal.hrm_demo.dto.response.CustomPage;
import conal.hrm_demo.entity.Employee;
import conal.hrm_demo.repository.EmployeeRepository;
import conal.hrm_demo.repository.specification.EmployeeSpecification;
import conal.hrm_demo.util.Generate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeSpecification employeeSpecification;


    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
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
            throw Generate.throwNotFoundExceptionMessage("Employee is not found!!");
        });
    }

    @Override
    public Employee addEmployee(Employee employee) {
        if (employeeRepository.existsByCode(employee.getCode()))
            throw Generate.throwNotFoundExceptionMessage("Employee Code is already exist");
        if (employeeRepository.existsByEmail(employee.getEmail()))
            throw Generate.throwNotFoundExceptionMessage("Employee email is already exist");
        if (employeeRepository.existsByPhone(employee.getPhone()))
            throw Generate.throwNotFoundExceptionMessage("Employee phone is already exist");
        return this.saveEmployee(employee);
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        return this.saveEmployee(employee);
    }

    @Override
    public CustomPage<Employee> getAllEmployeesWithPaging(EmployeeFilterRequest filterRequest) {
        Specification<Employee> specification = employeeSpecification.doFilter(
                filterRequest.getName(),
                filterRequest.getDirection(),
                filterRequest.getSortField(),
                filterRequest.getAddress(),
                filterRequest.getCode(),
                filterRequest.getPhone(),
                filterRequest.getEmail()
        );
        Pageable pageable = PageRequest.of(filterRequest.getPage(), filterRequest.getSize());
        return new CustomPage<>(employeeRepository.findAll(specification, pageable));
    }

    @Override
    public CustomPage<Employee> getAllEmployeesByDepartmentId(Long departmentId, int page,
                                                              int size,
                                                              boolean sort,
                                                              String sortField) {
        this.departmentService.getDepartmentByID(departmentId);
        Pageable pageable = PageRequest.of(page, size);
        return new CustomPage<>(employeeRepository.findAllByDepartmentId(pageable, departmentId));
    }

    @Override
    public List<Employee> getAllEmployeesByDepartmentId(Long departmentId) {
        return employeeRepository.findAllByDepartmentId(departmentId);
    }


    @Override
    public Employee getNthPaidEmployee(int position) {
        final var maps = findTotalPaymentOfEachEmployee(this.getAllEmployees());
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


}
