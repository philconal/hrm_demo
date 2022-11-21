package conal.hrm_demo.dto.request;


import conal.hrm_demo.dto.ParamError;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class CreateDepartmentRequest {
    @NotBlank(message = ParamError.FIELD_NAME)
    private String name;
    private String address;
    private int maxNoOfEmployee;
    private int currentNoOfEmployee;
}
