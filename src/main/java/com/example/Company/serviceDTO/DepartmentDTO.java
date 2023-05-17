package com.example.Company.serviceDTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.time.LocalDate;
import java.util.List;


@Data
@Getter
@Setter
@NoArgsConstructor
public class DepartmentDTO {
        private Long departmentid;
        private String name;
        @JsonIgnore
        private LocalDate startDate;
        private List<ProjectDTO> projects;

}

