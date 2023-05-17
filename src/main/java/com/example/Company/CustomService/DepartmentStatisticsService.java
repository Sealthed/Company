package com.example.Company.CustomService;

import com.example.Company.CustomDTO.DepartmentStatistics;
import com.example.Company.entity.Department;
import com.example.Company.entity.Employee;
import com.example.Company.entity.Gender;
import com.example.Company.repository.DepartmentRepository;
import com.example.Company.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentStatisticsService{

    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public DepartmentStatisticsService(DepartmentRepository departmentRepository, EmployeeRepository employeeRepository) {
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
    }

    public Map<String, DepartmentStatistics> generateDepartmentStatisticsReport() {
        // Retrieve departments
        List<Department> departments = departmentRepository.findAll();

        // Perform data processing using streams and collect the statistics
        Map<String, DepartmentStatistics> departmentStatisticsMap = departments.stream()
                .collect(Collectors.toMap(
                        Department::getName,
                        department -> {
                            List<Employee> employees = employeeRepository.findByDepartment(department);
                            long numberOfEmployees = employees.size();
                            long numberOfFemales = employees.stream().filter(e -> e.getGender() == Gender.Female).count();
                            long numberOfMales = employees.stream().filter(e -> e.getGender() == Gender.Male).count();
                            long numberOfU23 = employees.stream().filter(e -> calculateAge(e.getDateOfBirth()) < 23).count();
                            return new DepartmentStatistics(numberOfEmployees, numberOfFemales, numberOfMales, numberOfU23);
                        }
                ));
        return departmentStatisticsMap;
    }

    private int calculateAge(LocalDate dateOfBirth) {
        return LocalDate.now().getYear() - dateOfBirth.getYear();
    }
}
