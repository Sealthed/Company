package com.example.Company.service;

import com.example.Company.repository.DepartmentRepository;
import com.example.Company.serviceDTO.DepartmentDTO;
import com.example.Company.entity.Department;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public List<DepartmentDTO> getAllDepartments() {
        List<Department> departments = departmentRepository.findAll();
        return departments.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public DepartmentDTO getDepartmentById(Long id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));
        return convertToDTO(department);
    }

    //Add method that delete all departments
    public void deleteAllDepartments() {
        departmentRepository.deleteAll();
    }

    public DepartmentDTO createDepartment(DepartmentDTO departmentDTO) {
        Department department = new Department();
        department.setStartDate(departmentDTO.getStartDate());
        department.setName(departmentDTO.getName());
        Department savedDepartment = departmentRepository.save(department);
        return convertToDTO(savedDepartment);
    }

    //Find department with startdate between two dates (This is unoptimized, but it works)
    public List<DepartmentDTO> getDepartmentByStartDateBetween(LocalDate startDate, LocalDate endDate) {
        List<Department> departments = departmentRepository.findByStartDateBetween(startDate, endDate);
        return departments.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public void deleteDepartmentById(Long id) {
        departmentRepository.deleteById(id);
    }

    public DepartmentDTO updateDepartment(Long id, DepartmentDTO departmentDTO) {
        Department department = departmentRepository.findById(id).orElseThrow(() -> new RuntimeException("Department not found with id: " + id));
        department.setName(departmentDTO.getName());
        department.setStartDate(departmentDTO.getStartDate());
        Department updatedDepartment = departmentRepository.save(department);
        return convertToDTO(updatedDepartment);
    }


    private DepartmentDTO convertToDTO(Department department) {
        DepartmentDTO dto = new DepartmentDTO();
        dto.setDepartmentid(department.getDepartmentid());
        dto.setStartDate(department.getStartDate());
        dto.setName(department.getName());
        return dto;
    }
}

