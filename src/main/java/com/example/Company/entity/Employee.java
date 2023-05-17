package com.example.Company.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

//This Employee class uses @entity, @data, @noargsconstructor, @id, @generatedvalue, @column,@table
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table (name = "Employee")
@NamedNativeQueries({
        @NamedNativeQuery(
                name = "Employee.findByMaleGender",
                query = "SELECT * FROM Employee e WHERE e.gender = 'Male'",
                resultClass = Employee.class
        ),
        @NamedNativeQuery(
            name = "Employee.findByFemaleGender",
            query = "SELECT * FROM Employee e WHERE e.gender = 'Female'",
            resultClass = Employee.class
        ),
        @NamedNativeQuery(
                name = "Employee.findMaxSalary",
                query = "SELECT MAX(e.salary) FROM Employee e",
                resultSetMapping = "salaryMapping"
        ),
        @NamedNativeQuery(
                name = "Employee.findEmployeeWithMaxSalary",
                query = "SELECT * FROM Employee e WHERE e.salary = (SELECT MAX(salary) FROM Employee)",
                resultClass = Employee.class
        )
})
@SqlResultSetMapping(
        name = "salaryMapping",
        columns = {@ColumnResult(name = "max_salary", type = Integer.class)}
)
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
    @JoinColumn(name = "deptid")
    private Department department;

    @OneToMany(mappedBy = "employee")
    private List<Relatives> relatives;

    //@One to many with assignment
    @OneToMany(mappedBy = "employee")
    private Collection<Assignment> assignment;

}
