package com.example.Company.CustomDTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DepartmentStatistics {
    private long numberOfEmployees;
    private long numberOfFemales;
    private long numberOfMales;
    private long numberOfU23;
}
