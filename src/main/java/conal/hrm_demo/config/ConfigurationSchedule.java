package conal.hrm_demo.config;

import conal.hrm_demo.controller.helper.Generate;
import conal.hrm_demo.dto.request.CreateSalaryRequest;
import conal.hrm_demo.entity.Employee;
import conal.hrm_demo.services.ConfigurationService;
import conal.hrm_demo.services.EmployeeService;
import conal.hrm_demo.services.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Configuration
@EnableScheduling
public class ConfigurationSchedule implements SchedulingConfigurer {
    @Autowired
    private ConfigurationService configurationService;
    @Autowired
    private SalaryService salaryService;
    @Autowired
    private EmployeeService employeeService;
    private boolean isFirstTimeInit = true;

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.addTriggerTask(() -> {
            List<Employee> employees = this.employeeService.getAllEmployees();
            employees.forEach((item) -> {
                CreateSalaryRequest request =
                        CreateSalaryRequest.builder()
                                .bonus(this.getRandomBonus())
                                .note("Your income this time")
                                .amount(this.getRandomSalary())
                                .datePaid(new Date())
                                .employee_id(item.getId())
                                .code(Generate.generateString().toUpperCase())
                                .build();
                this.salaryService.addSalary(request);
            });
            System.out.println("Add salary");
        }, triggerContext -> {
            Calendar nextExecutionTime = new GregorianCalendar();
            Date lastActualExecutionTime = triggerContext.lastActualExecutionTime();
            nextExecutionTime.setTime(lastActualExecutionTime != null ? lastActualExecutionTime : new Date());
            nextExecutionTime.add(Calendar.MILLISECOND, getNewExecutionTime());
            return nextExecutionTime.getTime();
        });
    }

    private int getNewExecutionTime() {
        conal.hrm_demo.entity.Configuration scheduleTime = configurationService.getScheduleTime();
//        if (isFirstTimeInit) {
//            if ((new Date().getMinutes() % configurationService.getCurrentRangeTime()) != 0) {
//                isFirstTimeInit = false;
//                System.out.println(configurationService.getCurrentRangeTime() - new Date().getMinutes());
//                return (configurationService.getCurrentRangeTime() - new Date().getMinutes()) * 60000;
//            } else {
//                System.out.println("---------");
//                return 10000;
//            }
//        } else
            return scheduleTime.getScheduleTimeRepeat().intValue();

    }

    private double getRandomSalary() {
        return ((Math.random() * (300000000 - 7000000)) + 7000000);
    }

    private double getRandomBonus() {
        return ((Math.random() * (50000000 - 7000000)) + 5000000);
    }
}