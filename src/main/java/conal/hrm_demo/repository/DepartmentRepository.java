package conal.hrm_demo.repository;

import conal.hrm_demo.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    @Query(value = "select * from department d where d.id = :id", nativeQuery = true)
    Optional<Department> findById(Long id);
}
