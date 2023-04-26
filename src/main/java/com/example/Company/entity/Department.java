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
