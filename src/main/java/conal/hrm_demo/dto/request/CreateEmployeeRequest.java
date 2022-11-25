package conal.hrm_demo.dto.request;


import lombok.Data;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
public class CreateEmployeeRequest {
    @NotBlank(message = "First name should not be blank")
    private String firstName;
    private String lastName;
    @NotBlank(message = "Phone should not be blank")
    private String phone;
    @NotBlank(message = "Email should not be blank")
    private String email;
    private String address;
    private Date startedDate;
    private Date endedDate;
    private Long departmentId;
    private String code;

}
