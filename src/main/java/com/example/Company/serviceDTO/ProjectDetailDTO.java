package com.example.Company.serviceDTO;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ProjectDetailDTO {
    private Long projectId;
    private String projectName;
    private String area;
    private Integer totalNumberOfEmployees;
    private Integer totalNumberOfHours;
}
