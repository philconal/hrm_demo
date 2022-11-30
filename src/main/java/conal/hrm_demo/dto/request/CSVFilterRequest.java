package conal.hrm_demo.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CSVFilterRequest {
    private String fromDate;
    private String toDate;
    private String departmentId;
    private String employeeId;
}
