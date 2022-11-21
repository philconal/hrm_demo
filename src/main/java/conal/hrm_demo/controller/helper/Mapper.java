package conal.hrm_demo.controller.helper;

import conal.hrm_demo.dto.request.CreateDepartmentRequest;
import conal.hrm_demo.dto.request.CreateEmployeeRequest;
import conal.hrm_demo.dto.request.UpdateDepartmentRequest;
import conal.hrm_demo.dto.request.UpdateEmployeeRequest;
import conal.hrm_demo.entity.Department;
import conal.hrm_demo.entity.Employee;
import conal.hrm_demo.services.DepartmentService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Data
public class Mapper {


    public static Department map(CreateDepartmentRequest request) {
        Department department = new Department();
        department.setActive(true);
        department.setAddress(request.getAddress());
        department.setName(request.getName());
        department.setMaxNoOfEmployee(request.getMaxNoOfEmployee());
        department.setCurrentNoOfEmployee(request.getCurrentNoOfEmployee());
        department.setCreatedDate(LocalDate.now());
        department.setUpdatedDate(LocalDate.now());
        return department;
    }

    public static Department map(Department department, UpdateDepartmentRequest request) {
        department.setActive(request.isActive() );
        department.setAddress((request.getAddress() != null ? request.getAddress() : department.getAddress()));
        department.setName(request.getName() != null ? request.getName() : department.getName());
        department.setMaxNoOfEmployee(request.getMaxNoOfEmployee());
        department.setCurrentNoOfEmployee(request.getCurrentNoOfEmployee());
        department.setUpdatedDate(LocalDate.now());
        return department;
    }

    public static Employee map(CreateEmployeeRequest request) {
        //Check email regex
        //Check phone valid
        Employee employee = new Employee();
        employee.setActive(true);
        employee.setAddress(request.getAddress());
        employee.setEmail(request.getEmail());
        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setPhone(request.getPhone());
        employee.setStartedDate(request.getStartedDate());
        employee.setEndedDate(request.getEndedDate());
        employee.setUpdatedDate(LocalDate.now());
        employee.setCreatedDate(LocalDate.now());
        return employee;
    }

    public static Employee map(Employee employee, UpdateEmployeeRequest request) {
        //Check email regex
        //Check phone valid
        employee.setActive(request.isActive());
        employee.setAddress(request.getAddress());
        employee.setEmail(request.getEmail());
        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setPhone(request.getPhone());
        employee.setStartedDate(request.getStartedDate());
        employee.setEndedDate(request.getEndedDate());
        employee.setUpdatedDate(LocalDate.now());
        return employee;
    }
}
