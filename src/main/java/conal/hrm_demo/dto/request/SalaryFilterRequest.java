package conal.hrm_demo.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalaryFilterRequest {
    Long departmentId;
    int page;
    int size;


}
