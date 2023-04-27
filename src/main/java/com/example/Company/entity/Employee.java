package com.example.Company.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

//This Employee class uses @entity, @data, @noargsconstructor, @id, @generatedvalue, @column,@table
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table (name = "Employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employeeid", length = 9)
    private Long employeeId;

    //create date_of_birth column
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    //create first_name column
    @Column(name = "first_name", length = 20)
    private String firstName;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    //create last_name column
    @Column(name = "last_name", length = 20)
    private String lastName;

    //create middle_name column
    @Column(name = "middle_name", length = 20)
    private String middleName;

    //create salary column
    @Column(name = "salary")
    private Integer salary;

    //create deptid columndate
    @ManyToOne
    @JoinColumn(name = "deptid", referencedColumnName = "departmentid")
    private Department department;
}
