package conal.hrm_demo.repository.specification;

import conal.hrm_demo.entity.Department;
import conal.hrm_demo.entity.Employee;
import conal.hrm_demo.entity.Salary;
import conal.hrm_demo.exception.ApplicationException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class SalarySpecification {
    public Specification<Salary> doFilter(String startedFrom, String startedTo, String employeeId, String departmentId
    ) {
        return (clazzRoot, cq, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            Join<Employee, Salary> employeeSalaryJoin = clazzRoot.join("employee");
            Join<Employee, Department> departmentEmployeeJoin = employeeSalaryJoin.join("department");
            boolean isValidEmployeeID = employeeId != null && !employeeId.isBlank();
            boolean isValidDepartmentID = departmentId != null && !departmentId.isBlank();
            boolean isValidStartFromDate = startedFrom != null && !startedFrom.isBlank();
            boolean isValidStartToDate = startedTo != null && !startedTo.isBlank();
            if (isValidEmployeeID) {
                predicates.add(cb.equal(employeeSalaryJoin.get("id"), Long.parseLong(employeeId)));
            }
            if (isValidDepartmentID) {
                predicates.add(cb.equal(departmentEmployeeJoin.get("id"), Long.parseLong(departmentId)));
            }
            if (isValidStartFromDate && isValidStartToDate) {
                DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy,HH:mm:ss");
                try {
                    Date startDateFrom = formatter.parse(startedFrom);
                    Date startDateTo = formatter.parse(startedTo);
                    predicates.add(cb.between(clazzRoot.get("datePaid"), startDateFrom, startDateTo));
                } catch (ParseException e) {
                    throw new ApplicationException(HttpStatus.BAD_REQUEST, "Date should follow this format (dd-MM-yyyy,HH:mm:ss)");
                }
            }
            cq.orderBy(List.of(
                    cb.asc(clazzRoot.get("datePaid")),
                    cb.asc(employeeSalaryJoin.get("code")),
                    cb.asc(clazzRoot.get("amount")),
                    cb.asc(clazzRoot.get("bonus"))));
            return cb.and(predicates.toArray(new Predicate[]{}));
        };
    }

}

