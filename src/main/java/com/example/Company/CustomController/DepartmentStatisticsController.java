package com.example.Company.CustomController;

import com.example.Company.CustomDTO.DepartmentStatistics;
import com.example.Company.CustomService.DepartmentStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/department-statistics")
public class DepartmentStatisticsController {
    private final DepartmentStatisticsService departmentStatisticsService;
    @Autowired
    public DepartmentStatisticsController(DepartmentStatisticsService departmentStatisticsService) {
        this.departmentStatisticsService = departmentStatisticsService;
    }
    @GetMapping // http://localhost:8080/api/department-statistics
    public Map<String, DepartmentStatistics> getDepartmentStatisticsReport() {
        return departmentStatisticsService.generateDepartmentStatisticsReport();
    }
}