package conal.hrm_demo.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MoveEmployeeRequest {
    private Long fromDepartmentId;
    private boolean isMoveAll;
    private List<DepartmentMoveEmployeeMap> employeeMaps;
    private Long toDepartmentId;

    public MoveEmployeeRequest(Long fromDepartmentId, boolean isMoveAll, Long toDepartmentId) {
        this.fromDepartmentId = fromDepartmentId;
        this.isMoveAll = isMoveAll;
        this.toDepartmentId = toDepartmentId;
        this.employeeMaps = null;
    }

    public MoveEmployeeRequest(Long fromDepartmentId, boolean isMoveAll, List<DepartmentMoveEmployeeMap> employeeMaps) {
        this.fromDepartmentId = fromDepartmentId;
        this.isMoveAll = isMoveAll;
        this.employeeMaps = employeeMaps;
        this.toDepartmentId = null;
    }
}
