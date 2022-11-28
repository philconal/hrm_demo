package conal.hrm_demo.services;

import conal.hrm_demo.dto.request.DepartmentFilterRequest;
import conal.hrm_demo.dto.request.MoveEmployeeRequest;
import conal.hrm_demo.dto.response.CustomPage;
import conal.hrm_demo.entity.Department;
import org.springframework.data.domain.Page;

import java.util.List;

public interface DepartmentService {
//    Department saveDepartment(Department department);

    List<Department> getAllDepartments();

    Department getDepartmentByID(Long id);
    CustomPage<Department> getAllDepartmentsWithPaging(DepartmentFilterRequest request);
    Department updateDepartment(Department department);
    Department createDepartment(Department department);

    void deleteDepartment(Long id);
    boolean isDepartmentCodeExisted(String code);
    void moveEmployeesToOtherDepartment(MoveEmployeeRequest request);
}
