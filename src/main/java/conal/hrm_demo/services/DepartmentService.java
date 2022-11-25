package conal.hrm_demo.services;

import conal.hrm_demo.dto.request.CreateDepartmentRequest;
import conal.hrm_demo.entity.Department;
import org.springframework.data.domain.Page;

import java.util.List;

public interface DepartmentService {
//    Department saveDepartment(Department department);

    List<Department> getAllDepartments();

    Department getDepartmentByID(Long id);
    Page<List<Department>> getAllDepartmentsWithPaging();
    Department updateDepartment(Department department);
    Department createDepartment(Department department);

    Department deleteDepartment(Long id);


}
