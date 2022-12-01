package conal.hrm_demo.repository;

import conal.hrm_demo.dto.response.CustomPage;
import conal.hrm_demo.entity.Employee;
import conal.hrm_demo.entity.Salary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.io.ByteArrayInputStream;
import java.util.Optional;

@Repository
public interface SalaryRepository extends JpaRepository<Salary, Long>, JpaSpecificationExecutor<Salary> {
    @Query(value = "select * from salary s where s.id =:id and s.is_active = true", nativeQuery = true)
    Optional<Salary> findByOne(Long id);
    @Query(nativeQuery = true, value = "select * from salary e where e.salary_id =:id")
    Page<Salary> findAllByEmployeeId(Pageable pageable, Long id);

    boolean existsByCode(String code);
}
