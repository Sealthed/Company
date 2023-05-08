package com.example.Company.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table (name = "Department")
//Add some @NamedQueries
@NamedQueries(
    {
        //Add a simple query
        @NamedQuery(
            name = "Department.findByStartDate",
            query = "SELECT d FROM Department d WHERE d.startDate = ?1"
        ),
            //Add a @NamedQuery with join
        @NamedQuery(
            name = "Department.findByLocation",
            query = "SELECT d FROM Department d JOIN d.departmentLocation dl WHERE dl.location = ?1"),
        //Add a @NamedQuery that uses count with join
        @NamedQuery(
            name = "Department.countByLocation",
            query = "SELECT COUNT(d) FROM Department d JOIN d.departmentLocation dl WHERE dl.location = ?1"
        )
    }
)
public class Department {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long departmentid;

    @Column (name = "Department_name", unique = true)
    private String name;

    //start_date, date
    @Column (name = "start_date")
    private LocalDate startDate;

    //departmentid, one-to-many relationship with Project
    @OneToMany (mappedBy = "department")
    private List<Project> project;

    //departmentid, one-to-many relationship with DepartmentLocation
    @OneToMany (mappedBy = "department")
    private List<DepartmentLocation> departmentLocation;

    //departmentid, one-to-many relationship with Employee
    @OneToMany (mappedBy = "department")
    private List<Employee> employee;

}
