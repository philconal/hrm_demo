package conal.hrm_demo.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@RequiredArgsConstructor
@NotBlank
@AllArgsConstructor
@Builder
public class CreateSalaryRequest {
    private double amount;
    private double bonus;
    private String note;
    private Date datePaid;
    private Long employee_id;
}
