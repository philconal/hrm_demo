package conal.hrm_demo.services;

import conal.hrm_demo.dto.request.DepartmentFilterRequest;
import conal.hrm_demo.entity.Department;
import conal.hrm_demo.exception.ApplicationException;
import conal.hrm_demo.repository.DepartmentRepository;
import conal.hrm_demo.repository.specification.DepartmentSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private DepartmentSpecification departmentSpecification;

    @Override
    public Department deleteDepartment(Long id) {
        Department department = this.getDepartmentByID(id);
        department.setActive(false);
        return departmentRepository.save(department);
    }

    @Override
    public boolean isDepartmentCodeExisted(String code) {
        return departmentRepository.existsByCode(code);
    }

    @Override
    public Department getDepartmentByID(Long id) {
        System.out.println(id);
        Optional<Department> department = departmentRepository.findById(id);
        return department.orElseThrow(() -> new ApplicationException(HttpStatus.BAD_REQUEST, "Department is not found!!"));
    }

    @Override
    public Page<Department> getAllDepartmentsWithPaging(DepartmentFilterRequest request) {
        Specification<Department> specification = departmentSpecification.doFilter(
                request.getName(),
                request.isSort(),
                request.getSortField(),
                request.getAddress(),
                request.getCode());
        Pageable pageable = PageRequest.of(request.getPage() - 1, request.getSize());
        return departmentRepository.findAll(specification, pageable);
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
