package com.example.Company.serviceDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
@Data
@NoArgsConstructor
public class DepartmentDTO {
        private Long departmentid;
        private String name;
        private Date startDate;
}

