package conal.hrm_demo.controller.helper;

import conal.hrm_demo.dto.request.*;
import conal.hrm_demo.entity.Department;
import conal.hrm_demo.entity.Employee;
import conal.hrm_demo.entity.Salary;
import lombok.Data;

import java.util.Date;


@Data
public class Mapper {
    public static Department map(CreateDepartmentRequest request) {

        return Department.builder()
                .isActive(true)
                .address(request.getAddress())
                .name(request.getName())
                .code(request.getCode())
                .maxNoOfEmployee(request.getMaxNoOfEmployee())
                .currentNoOfEmployee(request.getCurrentNoOfEmployee())
                .createdDate(new Date())
                .updatedDate(new Date())
                .build();
    }

    public static Department map(Department department, UpdateDepartmentRequest request) {
        department.setCode(request.getCode() != null ? request.getCode() : department.getCode());
        department.setAddress((request.getAddress() != null ? request.getAddress() : department.getAddress()));
        department.setName((request.getName() != null ? request.getName() : department.getName()));
        department.setMaxNoOfEmployee(request.getMaxNoOfEmployee() != null ? Integer.parseInt(request.getMaxNoOfEmployee()) : department.getMaxNoOfEmployee());
        department.setCurrentNoOfEmployee(request.getCurrentNoOfEmployee() != null ? Integer.parseInt(request.getCurrentNoOfEmployee()) : department.getCurrentNoOfEmployee());
        department.setUpdatedDate(new Date());
        return department;
    }

    public static Employee map(CreateEmployeeRequest request) {
        Employee employee = new Employee();
        employee.setActive(true);
        employee.setAddress(request.getAddress());
        employee.setEmail(request.getEmail());
        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setPhone(request.getPhone());
        employee.setStartedDate(request.getStartedDate());
        employee.setEndedDate(request.getEndedDate());
        employee.setUpdatedDate(new Date());
        employee.setCreatedDate(new Date());
        employee.setCode(request.getCode());
        return employee;
    }

    public static Employee map(Employee employee, UpdateEmployeeRequest request) {
        employee.setAddress(request.getAddress() != null ? request.getAddress() : employee.getAddress());
        employee.setEmail(request.getEmail() != null ? request.getEmail() : employee.getEmail());
        employee.setFirstName(request.getFirstName() != null ? request.getFirstName() : employee.getFirstName());
        employee.setLastName(request.getLastName() != null ? request.getLastName() : employee.getLastName());
        employee.setPhone(request.getPhone() != null ? request.getPhone() : employee.getPhone());
        employee.setCode(request.getCode() != null ? request.getCode() : employee.getCode());
        employee.setStartedDate(request.getStartedDate());
        employee.setEndedDate(request.getEndedDate());
        employee.setUpdatedDate(new Date());
        return employee;
    }

    public static Salary map(Employee employee, CreateSalaryRequest request) {
        Salary salary = new Salary();
        salary.setAmount(request.getAmount());
        salary.setBonus(request.getBonus());
        salary.setNote(request.getNote());
        salary.setCreatedDate(new Date());
        salary.setUpdatedDate(new Date());
        salary.setDatePaid(request.getDatePaid());
        salary.setEmployee(employee);
        return salary;
    }

    public static Salary map(Salary salary, Employee employee, UpdateSalaryRequest request) {
        salary.setAmount(request.getAmount());
        salary.setBonus(request.getBonus());
        salary.setNote(request.getNote());
        salary.setUpdatedDate(new Date());
        salary.setDatePaid(request.getDatePaid());
        salary.setEmployee(employee);
        return salary;
    }
}
