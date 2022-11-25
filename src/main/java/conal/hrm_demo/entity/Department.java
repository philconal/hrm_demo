package conal.hrm_demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import conal.hrm_demo.dto.ParamError;
import conal.hrm_demo.util.Constant;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
@ToString
public class Department implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = ParamError.FIELD_NAME)
    private String name;
    @NotBlank(message = ParamError.FIELD_NAME,groups = {})
    private String address;
    @NotBlank(message = ParamError.FIELD_NAME)
    @Column(unique = true, nullable = false)
    private String code;
    private int maxNoOfEmployee;
    private int currentNoOfEmployee;
    private boolean isActive = true;
    @Column(updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constant.API_FORMAT_DATE_TIME)
    private Date createdDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constant.API_FORMAT_DATE_TIME)
    private Date updatedDate;
    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "department"
    )
    private List<Employee> employees;

}
