package conal.hrm_demo.services;

import conal.hrm_demo.dto.request.DepartmentFilterRequest;
import conal.hrm_demo.dto.request.DepartmentMoveEmployeeMap;
import conal.hrm_demo.dto.request.MoveEmployeeRequest;
import conal.hrm_demo.dto.response.CustomPage;
import conal.hrm_demo.entity.Department;
import conal.hrm_demo.entity.Employee;
import conal.hrm_demo.exception.ApplicationException;
import conal.hrm_demo.repository.DepartmentRepository;
import conal.hrm_demo.repository.EmployeeRepository;
import conal.hrm_demo.repository.specification.DepartmentSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired

    private EmployeeService employeeService;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private DepartmentSpecification departmentSpecification;


    @Override
    public void deleteDepartment(Long id) {
        Department department = this.getDepartmentByID(id);
        department.setActive(false);
        departmentRepository.save(department);
    }

    @Override
    public boolean isDepartmentCodeExisted(String code) {
        return departmentRepository.existsByCode(code);
    }

    @Override
    public void moveEmployeesToOtherDepartment(MoveEmployeeRequest request) {
        if (request.isMoveAll() && (request.getEmployeeMaps() == null || request.getEmployeeMaps().isEmpty())) {
            List<Employee> employees = this.employeeService.getAllEmployeesByDepartmentId(request.getFromDepartmentId());
            Department departmentByID = this.getDepartmentByID(request.getToDepartmentId());
            for (Employee map : employees) {
                map.setDepartment(departmentByID);
                this.employeeService.updateEmployee(map);
            }
        } else {
            List<Employee> employeeList = new ArrayList<>();
            for (DepartmentMoveEmployeeMap map : request.getEmployeeMaps()) {
                Department departmentByID = this.getDepartmentByID(map.getDepartmentId());
                Employee employeeID = this.employeeService.getEmployeeByID(map.getEmployeeId());
                employeeID.setDepartment(departmentByID);
                employeeList.add(employeeID);
            }
            for (Employee employee : employeeList
            ) {
                this.employeeService.updateEmployee(employee);
            }
        }
    }

    @Override
    public Department getDepartmentByID(Long id) {
        Optional<Department> department = departmentRepository.findById(id);
        return department.orElseThrow(() -> new ApplicationException(HttpStatus.BAD_REQUEST, "Department is not found!!"));
    }

    @Override
    public CustomPage<Department> getAllDepartmentsWithPaging(DepartmentFilterRequest request) {
        Specification<Department> specification = departmentSpecification.doFilter(request.getName(), request.getDirection(), request.getSortField(), request.getAddress(), request.getCode());
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        return new CustomPage<Department>(departmentRepository.findAll(specification, pageable));
    }

    @Override
    public Department updateDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public Department createDepartment(Department department) {
        if (departmentRepository.existsByCode(department.getCode()))
            throw new ApplicationException(HttpStatus.BAD_REQUEST, "Department Code is already exist");
        return departmentRepository.save(department);
    }

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAllActiveDepartment();
    }

}
