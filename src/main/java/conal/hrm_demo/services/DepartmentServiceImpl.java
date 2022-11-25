package conal.hrm_demo.services;

import conal.hrm_demo.entity.Department;
import conal.hrm_demo.exception.ApplicationException;
import conal.hrm_demo.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Department deleteDepartment(Long id) {
        Department department = this.getDepartmentByID(id);
        department.setActive(false);
        return departmentRepository.save(department);
    }

    @Override
    public Department getDepartmentByID(Long id) {
        Optional<Department> department = departmentRepository.findById(id);
        return department.orElseThrow(() -> new ApplicationException(HttpStatus.BAD_REQUEST, "Department is not found!!"));
    }

    @Override
    public Page<List<Department>> getAllDepartmentsWithPaging() {
        return null;
    }

    @Override
    public Department updateDepartment(Department department) {
        if (departmentRepository.existsByCode(department.getCode()))
            throw new ApplicationException(HttpStatus.BAD_REQUEST, "Department Code is already exist");
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
        return departmentRepository.findAll();
    }

}
