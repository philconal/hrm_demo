package conal.hrm_demo.repository.specification;

import conal.hrm_demo.entity.Employee;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

@Component
public class EmployeeSpecification {
    public Specification<Employee> doFilter(String name, boolean sort, String sortField, String address, String code, String phone, String email
            ) {
        return (Root<Employee> clazzRoot, CriteriaQuery<?> cq, CriteriaBuilder cb) -> {
            cq.distinct(true);
            List<Predicate> predicates = new ArrayList<>();

            predicates.add(cb.equal(clazzRoot.get("isActive"), true));

            addFilterByProperty(predicates, clazzRoot, cq, cb, code, "code");
            addFilterByProperty(predicates, clazzRoot, cq, cb, name, "firstName");
            addFilterByProperty(predicates, clazzRoot, cq, cb, address, "address");
            addFilterByProperty(predicates, clazzRoot, cq, cb, phone, "phone");
            addFilterByProperty(predicates, clazzRoot, cq, cb, email, "email");
//            try {
//                if ((startedFrom != null && startedTo != null) && (!startedFrom.trim().isEmpty() && !startedTo.trim().isEmpty())) {
//                    Instant startFrom = fromString(startedFrom);
//                    Instant startTo = fromString(startedTo);
//                    predicates.add(cb.between(clazzRoot.get("startedDate"), startFrom, startTo));
//                }
//            } catch (DateTimeParseException dtpe) {
//                // invalid format, consider log the error, etcetera
//                dtpe.printStackTrace();
//            }
            Path orderClause = switch (sortField.trim()) {
                case "name" -> clazzRoot.get("firstName");
                case "address" -> clazzRoot.get("address");
                case "code" -> clazzRoot.get("code");
                case "phone" -> clazzRoot.get("phone");
                case "email" -> clazzRoot.get("email");
                default -> clazzRoot.get("createdDate");
            };

            if (sort) {
                cq.orderBy(cb.asc(orderClause));
            } else {
                cq.orderBy(cb.desc(orderClause));
            }

            return cb.and(predicates.toArray(new Predicate[]{}));

        };
    }

    private Instant fromString(final String localDateTimeString) throws DateTimeParseException {
        if (StringUtils.isEmpty(localDateTimeString)) {
            return null;
        }

        // Convert String to LocalDateTime
        LocalDateTime localDateTime = LocalDateTime.parse(localDateTimeString, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm", Locale.US));

        // Return the obtained instant
        return localDateTime.atZone(ZoneId.of("Asia/Kolkata")).toInstant();
    }

    private void addFilterByProperty(List<Predicate> predicates, Root<Employee> clazzRoot, CriteriaQuery<?> cq, CriteriaBuilder cb, String property, String queryName) {
        if (property != null && !property.trim().isEmpty()) {
            String propertySearch = property.trim();
            predicates.add(cb.or(cb.like(clazzRoot.get(queryName), "%" + propertySearch + "%")));
        }
    }
}
