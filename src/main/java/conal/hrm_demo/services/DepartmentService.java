package conal.hrm_demo.services;

import conal.hrm_demo.entity.Department;

import java.util.List;

public interface DepartmentService {
    Department saveDepartment(Department department);

    List<Department> getAllDepartments();

    Department deleteDepartment(Long id);

    Department getDepartmentByID(Long id);
}
