package conal.hrm_demo.repository;

import conal.hrm_demo.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentRepository extends PagingAndSortingRepository<Department, Long> {
    @Query(value = "select * from department d where d.id = :id and d.is_active = true", nativeQuery = true)
    Optional<Department> findById(Long id);

    @Query(value = "select * from department d where d.is_active = true", nativeQuery = true)
    List<Department> findAll();
    boolean existsByCode(String code);
}
