package conal.hrm_demo.repository;

import conal.hrm_demo.entity.Salary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SalaryRepository extends JpaRepository<Salary, Long> {
    @Query(value = "select * from salary s where s.id =:id and s.is_active = true", nativeQuery = true)
    Optional<Salary> findByOne(Long id);
    boolean existsByCode(String code);
}
