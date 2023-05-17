package com.example.Company.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table (name = "Relatives")
public class Relatives {
    @Id
    @GeneratedValue (strategy = javax.persistence.GenerationType.IDENTITY)
    private Long id;

    @Column (name = "fullName", length = 255)
    private String fullName;

    //Gender, int
    @Column (name = "gender")
    private int gender;


    @Column (name = "phoneNumber", length = 255)
    private String phoneNumber;

    @Column (name = "Relationship")
    private String relationship;

    @ManyToOne
    @JoinColumn (name = "Employeeid", referencedColumnName = "employeeid")
    private Employee employee;

}
