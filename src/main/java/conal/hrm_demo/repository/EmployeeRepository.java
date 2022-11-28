package conal.hrm_demo.repository;

import conal.hrm_demo.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>, JpaSpecificationExecutor<Employee> {
    @Query(nativeQuery = true, value = "select * from employee e where e.id =:id and e.is_active = true")
    Optional<Employee> findById(Long id);

    @Query(nativeQuery = true, value = "select * from employee e where e.department_id =:id and e.is_active = true")
    Page<Employee> findAllByDepartmentId(Pageable pageable, Long id);

    boolean existsByCode(String code);

    boolean existsByEmail(String email);

    boolean existsByPhone(String phone);

    boolean existsByDepartmentId(Long departmentId);
}
