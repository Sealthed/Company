package com.example.Company.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table (name = "Project")
public class Project {
    //generate id
    @Id
    @GeneratedValue (strategy = javax.persistence.GenerationType.IDENTITY)
    private Long projectid;

    //area, varchar length 100
    @Column (name = "area", length = 100)
    private String area;

    //project_name, varchar length 100
    @Column (name = "project_name", length = 100)
    private String projectName;

    //mangaed_department, bigint, references Department table
    @ManyToOne
    @JoinColumn (name = "mangaed_department", referencedColumnName = "departmentid")
    private Department department;
}
