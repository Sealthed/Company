package com.example.Company.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table (name = "DepartmentLocation")
public class DepartmentLocation {
    @Id
    @GeneratedValue (strategy = javax.persistence.GenerationType.IDENTITY)
    private Long id;

    @Column (name = "Location")
    private String location;

    @ManyToOne
    @JoinColumn (name = "Deptid", referencedColumnName = "departmentid")
    private Department department;
}
