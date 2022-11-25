package conal.hrm_demo.repository;

import conal.hrm_demo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query(nativeQuery = true, value = "select * from employee e where e.id =:id and e.is_active = true")
    Optional<Employee> findById(Long id);
    boolean existsByCode(String code);
    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);
    boolean existsByDepartmentId(Long departmentId);
}
