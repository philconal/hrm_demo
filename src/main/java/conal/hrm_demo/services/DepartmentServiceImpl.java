package conal.hrm_demo.services;

import conal.hrm_demo.entity.Department;
import conal.hrm_demo.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public Department deleteDepartment(Long id) {
        Department department = this.getDepartmentByID(id);
        department.setActive(false);
        return departmentRepository.save(department);
    }

    @Override
    public Department getDepartmentByID(Long id) {
        Optional<Department> department = departmentRepository.findById(id);
        return department.orElseThrow(() -> new IllegalArgumentException("Department is not found!!"));
    }

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

}
