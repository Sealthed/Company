package com.example.Company.serviceDTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
public class ProjectDTO {
    private Long id;
    private String name;
    private String area;
    @JsonIgnore
    private String departmentName;
}
