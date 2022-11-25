package conal.hrm_demo.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmployeeFilterRequest {
    private double salaryFrom;
    private double salaryTo;
    private String name;
    private String phone;
    private String email;
    private String address;
    private String startedFrom;
    private String endedFrom;
    private String departmentName;
    private String departmentCode;
    private int workingTime;
}
