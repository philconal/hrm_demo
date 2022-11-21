package conal.hrm_demo;

import conal.hrm_demo.entity.Department;
import conal.hrm_demo.entity.Employee;
import conal.hrm_demo.entity.Salary;
import conal.hrm_demo.services.DepartmentService;
import conal.hrm_demo.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class HrmDemoApplication implements CommandLineRunner {
    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private EmployeeService employeeService;


    public static void main(String[] args) {
        SpringApplication.run(HrmDemoApplication.class, args);
        System.out.println("The application has been running!!");
    }

    @Override
    public void run(String... args) throws Exception {
        Department department = Department.builder()
                .createdDate(LocalDate.now())
                .code("SD1234").id(1L)
                .name("Software Development")
                .address("Ban Vien Floor 3")
                .currentNoOfEmployee(15)
                .maxNoOfEmployee(20)
                .build();
        Employee employee = Employee.builder()
                .code("EMP1234")
                .isActive(true)
                .email("conal2411@gmail.com")
                .phone("09870189122")
                .id(1L)
                .address("Lam Dong province")
                .firstName("Phil")
                .lastName("Conal")
                .startedDate(LocalDate.now().minusDays(20))
                .department(department)
                .build();
        Salary salary = Salary.builder().
                id(1L)
                .bonus(200)
                .note("Overtime")
                .datePaid(LocalDate.now())
                .amount(15000000)
                .employee(employee)
                .build();

        this.departmentService.saveDepartment(department);
        this.employeeService.saveEmployee(employee);
    }
}
