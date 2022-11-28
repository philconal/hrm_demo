package conal.hrm_demo.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentFilterRequest {
    private String name;
    int page;
    int size;
    String direction;
    String sortField;
    String address;
    String code;
}
