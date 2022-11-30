package conal.hrm_demo;

import conal.hrm_demo.entity.Department;
import conal.hrm_demo.entity.Employee;
import conal.hrm_demo.services.ConfigurationService;
import conal.hrm_demo.services.DepartmentService;
import conal.hrm_demo.services.EmployeeService;
import conal.hrm_demo.services.SalaryService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;
import java.util.Random;


@SpringBootApplication
public class HrmDemoApplication implements CommandLineRunner {
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private SalaryService salaryService;
    @Autowired
    private ConfigurationService configurationService;

    public static void main(String[] args) {
        SpringApplication.run(HrmDemoApplication.class, args);
        System.out.println("The application has been running!!");
    }

    @Override
    public void run(String... args) throws Exception {
//        for (int i = 0; i < 5; i++) {
//            String code = "DP" + (i + 1);
//            String name = "Software Development " + (i + 1);
//            String address = "Ban Vien Floor " + (i + 1);
//            Department department = Department.builder()
//                    .createdDate(new Date())
//                    .code(code)
//                    .name(name)
//                    .address(address)
//                    .currentNoOfEmployee(15)
//                    .maxNoOfEmployee(20)
//                    .createdDate(new Date())
//                    .isActive(true)
//                    .build();
//            this.departmentService.createDepartment(department);
//        }
//        for (int i = 0; i < 5; i++) {
//            String code = "EM" + (i + 1);
//            String name = "Embemded Development " + (i + 1);
//            String address = "Ban Vien Floor " + (i + 1);
//            Department department = Department.builder()
//                    .createdDate(new Date())
//                    .code(code)
//                    .name(name)
//                    .address(address)
//                    .currentNoOfEmployee(5)
//                    .maxNoOfEmployee(20)
//                    .createdDate(new Date())
//                    .isActive(true)
//                    .build();
//            this.departmentService.createDepartment(department);
//        }
//        for (int i = 0; i < 5; i++) {
//            String code = "M" + (i + 1);
//            String name = "Marketing " + (i + 1);
//            String address = "HCM " + (i + 1);
//            Department department = Department.builder()
//                    .createdDate(new Date())
//                    .code(code)
//                    .name(name)
//                    .address(address)
//                    .currentNoOfEmployee(5)
//                    .maxNoOfEmployee(20)
//                    .isActive(true)
//                    .createdDate(new Date())
//                    .build();
//            this.departmentService.createDepartment(department);
//        }
//        for (int i = 0; i < 5; i++) {
//            String code = "AC" + (i + 1);
//            String name = "Accounting " + (i + 1);
//            String address = "Lam Dong " + (i + 1);
//            Department department = Department.builder()
//                    .createdDate(new Date())
//                    .code(code)
//                    .name(name)
//                    .address(address)
//                    .currentNoOfEmployee(5)
//                    .maxNoOfEmployee(20)
//                    .isActive(true)
//                    .createdDate(new Date())
//                    .build();
//            this.departmentService.createDepartment(department);
//        }
//        for (int i = 0; i < 5; i++) {
//            String code = "EMP00" + (i + 1);
//            Employee employee = Employee.builder()
//                    .code(code)
//                    .id(Long.parseLong((i + 1) + ""))
//                    .isActive(true)
//                    .createdDate(new Date())
//                    .email(generateString() + "@gmail.com")
//                    .phone(randomPhone())
//                    .address(generateString() + "province")
//                    .firstName(generateString())
//                    .lastName(generateString())
//                    .startedDate(new Date())
//                    .department(this.departmentService.getDepartmentByID(Long.parseLong((i + 1) + "")))
//                    .build();
//            this.employeeService.saveEmployee(employee);
//        }
//        for (int i = 0; i < 5; i++) {
//            String code = "EMP000" + (i + 1);
//            Employee employee = Employee.builder()
//                    .code(code)
//                    .id(Long.parseLong((i + 1) + ""))
//                    .isActive(true)
//                    .createdDate(new Date())
//                    .email(generateString() + "@gmail.com")
//                    .phone(randomPhone())
//                    .address(generateString() + "province")
//                    .firstName(generateString())
//                    .lastName(generateString())
//                    .startedDate(new Date())
//                    .department(this.departmentService.getDepartmentByID(Long.parseLong((i + 1) + "")))
//                    .build();
//            this.employeeService.saveEmployee(employee);
//        }
//        for (int i = 0; i < 5; i++) {
//            String code = "EP" + (i + 1);
//            Employee employee = Employee.builder()
//                    .code(code)
//                    .id(Long.parseLong((i + 1) + ""))
//                    .isActive(true)
//                    .createdDate(new Date())
//                    .email(generateString() + "@gmail.com")
//                    .phone(randomPhone())
//                    .address(generateString() + "province")
//                    .firstName(generateString())
//                    .lastName(generateString())
//                    .startedDate(new Date())
//                    .department(this.departmentService.getDepartmentByID(Long.parseLong((i + 1) + "")))
//                    .build();
//            this.employeeService.saveEmployee(employee);
//        }
//        for (int i = 0; i < 5; i++) {
//            String code = "NE0" + (i + 1);
//            Employee employee = Employee.builder()
//                    .code(code)
//                    .id(Long.parseLong((i + 1) + ""))
//                    .isActive(true)
//                    .createdDate(new Date())
//                    .email(generateString() + "@gmail.com")
//                    .phone(randomPhone())
//                    .address(generateString() + "province")
//                    .firstName(generateString())
//                    .lastName(generateString())
//                    .startedDate(new Date())
//                    .department(this.departmentService.getDepartmentByID(Long.parseLong((i + 1) + "")))
//                    .build();
//            this.employeeService.saveEmployee(employee);
//        }
//        setDefaultScheduleTime();
    }

    private void setDefaultScheduleTime() {
        configurationService.addDefaultScheduleTime();
    }

}
