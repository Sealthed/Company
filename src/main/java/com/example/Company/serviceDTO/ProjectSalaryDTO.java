package com.example.Company.serviceDTO;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ProjectSalaryDTO {
    private Long projectId;
    private String projectName;
    private String area;
    private Integer totalNumberOfHours;
    private Integer totalSalary;
}
