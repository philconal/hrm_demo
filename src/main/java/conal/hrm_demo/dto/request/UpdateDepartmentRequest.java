package conal.hrm_demo.dto.request;


import conal.hrm_demo.dto.ParamError;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UpdateDepartmentRequest {
    private String name;
    private String address;
    private String maxNoOfEmployee;
    private String currentNoOfEmployee;
    private String code;
}
