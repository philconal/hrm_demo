package conal.hrm_demo.dto.request;


import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@RequiredArgsConstructor
@NotBlank
@AllArgsConstructor
@Builder
@ToString
public class CreateSalaryRequest {
    private double amount;
    private double bonus;
    private String note;
    private String code;
    private Date datePaid;
    private Long employee_id;
}
