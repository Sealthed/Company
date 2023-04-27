package com.example.Company.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table (name = "Assignment")
public class Assignment {
    @Id
    @GeneratedValue (strategy = javax.persistence.GenerationType.IDENTITY)
    private Long id;

    // number_of_hours column with integer type
    @Column (name = "number_of_hours")
    private Integer numberOfHours;

    // employeeid column with varchar type, references Employee table
    @ManyToOne
    @JoinColumn (name = "employeeid", referencedColumnName = "employeeid")
    private Employee employee;


    @ManyToOne
    @JoinColumn (name = "projectid", referencedColumnName = "projectid")
    private Project project;
}
