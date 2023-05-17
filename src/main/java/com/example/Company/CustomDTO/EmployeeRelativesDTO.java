package com.example.Company.CustomDTO;

import com.example.Company.entity.Gender;
import com.example.Company.serviceDTO.RelativesDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class EmployeeRelativesDTO {
    private Long employeeId;
    private LocalDate dateOfBirth;
    private String firstName;
    private Gender gender;
    private String lastName;
    private String middleName;
    private Integer salary;
    private Long departmentId;
    private RelativesDTO relative;
}
