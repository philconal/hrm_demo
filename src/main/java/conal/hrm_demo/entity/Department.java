package conal.hrm_demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import conal.hrm_demo.dto.ParamError;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Department implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = ParamError.FIELD_NAME)
    private String name;
    @NotBlank(message = ParamError.FIELD_NAME)
    @Column(unique = true, nullable = false)
    private String code;
    private String address;
    private int maxNoOfEmployee;
    private int currentNoOfEmployee;
    private boolean isActive = true;
    private LocalDate createdDate;
    private LocalDate updatedDate;
    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "department"
    )
    @JsonIgnore
    private List<Employee> employees;
}
