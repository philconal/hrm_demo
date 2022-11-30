package conal.hrm_demo.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import conal.hrm_demo.dto.ParamError;
import conal.hrm_demo.util.Constant;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
    @Column(nullable = false, unique = true)
    private String phone;
    @Column(nullable = false, unique = true)
    @NotBlank(message = "Email should not be blank")
    private String email;
    private String address;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constant.API_FORMAT_DATE_TIME)
    private Date startedDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constant.API_FORMAT_DATE_TIME)
    private Date endedDate;
    private boolean isActive;
    @NotBlank(message = ParamError.FIELD_NAME)
    @Column(unique = true, nullable = false)
    private String code;
    @Column(updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constant.API_FORMAT_DATE_TIME)
    private Date createdDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constant.API_FORMAT_DATE_TIME)
    private Date updatedDate;
    @ManyToOne
    @JoinColumn(
            name = "department_id",
            nullable = false
    )
    @JsonIgnore
    private Department department;

    @OneToMany(mappedBy = "employee")
    private List<Salary> salaries;
}
