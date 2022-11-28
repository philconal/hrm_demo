package conal.hrm_demo.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeFilterRequest {
//    private String salaryFrom;
//    private String salaryTo;
    private String name;
    private String code;
    private String phone;
    private String email;
    private String address;
//    private String startedFrom;
//    private String startedTo;
//    private String endedFrom;
//    private String endedTo;
//    private String departmentName;
//    private String departmentCode;
    int page;
    int size;
    boolean sort;
    String sortField;
}
