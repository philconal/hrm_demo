package conal.hrm_demo.dto.request;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UpdateEmployeeRequest {
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String address;
    private Date startedDate;
    private Date endedDate;
    private String departmentId;
    private String code;
}
