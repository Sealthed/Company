package com.example.Company.serviceDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;


@Data
@NoArgsConstructor
public class DepartmentDTO {
        private Long departmentid;
        private String name;
        private LocalDate startDate;
}

