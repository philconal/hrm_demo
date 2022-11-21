package conal.hrm_demo.entity;


import conal.hrm_demo.dto.ParamError;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Employee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "First name should not be blank")
    private String firstName;
    private String lastName;
    @NotBlank(message = "Phone should not be blank")
    private String phone;
    @NotBlank(message = "Email should not be blank")
    private String email;
    private String address;
    private LocalDate startedDate;
    private LocalDate endedDate;
    private boolean isActive;
    @NotBlank(message = ParamError.FIELD_NAME)
    @Column(unique = true, nullable = false)
    private String code;
    private LocalDate createdDate;
    private LocalDate updatedDate;
    @ManyToOne
    @JoinColumn(
            name = "department_id",
            nullable = false
    )
    private Department department;

    @OneToOne(mappedBy = "employee")
    private Salary salary;
}
