package com.example.Company.serviceDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DepartmentLocationDTO {
    private Long id;
    private String location;
    private Long departmentId;
}
