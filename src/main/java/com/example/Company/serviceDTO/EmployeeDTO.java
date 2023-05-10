package com.example.Company.serviceDTO;

import com.example.Company.entity.Gender;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class EmployeeDTO {
    private Long employeeId;
    private LocalDate dateOfBirth;
    private String firstName;
    private Gender gender;
    private String lastName;
    private String middleName;
    private Integer salary;
    private Long departmentId;
}
