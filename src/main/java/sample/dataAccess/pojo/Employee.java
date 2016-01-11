package sample.dataAccess.pojo;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by Daniel on 2014-12-06.
 */
@Entity
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String email;

    @NotNull
    private String phone;

    @NotNull
    private double salary;

    @NotNull
    private Date employmentDate;

    @NotNull
    @ManyToOne(cascade={CascadeType.ALL}, fetch=FetchType.LAZY)
    @JoinColumn(name="departmentId")
    private DictDepartment department;


}
