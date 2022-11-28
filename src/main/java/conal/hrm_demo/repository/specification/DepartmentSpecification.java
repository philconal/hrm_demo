package conal.hrm_demo.repository.specification;

import conal.hrm_demo.entity.Department;
import conal.hrm_demo.entity.enums.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class DepartmentSpecification {
    public Specification<Department> doFilter(
            String name,
            String direction,
            String sortField,
            String address,
            String code
    ) {
        return (
                Root<Department> clazzRoot, CriteriaQuery<?> cq, CriteriaBuilder cb
        ) -> {
            cq.distinct(true);
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(cb.equal(clazzRoot.get("isActive"), true));
            addFilterByProperty(predicates,clazzRoot,cq,cb,code,"code");
            addFilterByProperty(predicates,clazzRoot,cq,cb,name,"name");
            addFilterByProperty(predicates,clazzRoot,cq,cb,address,"address");
            Path orderClause = switch (sortField.trim()) {
                case "name" -> clazzRoot.get("name");
                case "address" -> clazzRoot.get("address");
                case "code" -> clazzRoot.get("code");
                default -> clazzRoot.get("createdDate");
            };
            if (!direction.equals(Direction.UNSORTED.getDirection())) {
                if (direction.equals(Direction.ASC.getDirection())) {
                    cq.orderBy(cb.asc(orderClause));
                }
                if (direction.equals(Direction.DESC.getDirection())) {
                    cq.orderBy(cb.desc(orderClause));
                }
            }
            return cb.and(predicates.toArray(new Predicate[]{}));
        };
    }

    private void addFilterByProperty(List<Predicate> predicates, Root<Department> clazzRoot, CriteriaQuery<?> cq, CriteriaBuilder cb, String property,String queryName) {
        if (property != null && !property.trim().isEmpty()) {
            String propertySearch = property.trim();
            predicates.add(cb.or(
                    cb.like(cb.lower(clazzRoot.get(queryName)), "%" + propertySearch.toLowerCase() + "%")));
        }
    }
}
