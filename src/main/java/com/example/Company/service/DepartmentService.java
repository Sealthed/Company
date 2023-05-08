package com.example.Company.service;

import com.example.Company.repository.DepartmentRepository;
import com.example.Company.serviceDTO.DepartmentDTO;
import com.example.Company.entity.Department;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


    public DepartmentDTO createDepartment(DepartmentDTO departmentDTO) {
        Department department = new Department();
        department.setStartDate(departmentDTO.getStartDate());
        department.setName(departmentDTO.getName());
        Department savedDepartment = departmentRepository.save(department);
        return convertToDTO(savedDepartment);
    }

    public void deleteDepartmentById(Long id) {
        departmentRepository.deleteById(id);
    }

    //Update the service class to use named query from repository and department entity
    public List<DepartmentDTO> findDepartmentByStartDate(LocalDate date) {
        List<Department> departments = departmentRepository.findByStartDate(date);
        return departments.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    //Update the service class to use named query from repository and department entity
    public List<DepartmentDTO> findByStartDate(LocalDate date){
        List<Department> departments = departmentRepository.findByStartDate(date);
        return departments.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    //Update the service class to use named query from repository and department entity
    public List<DepartmentDTO> findByLocation(String location){
        List<Department> departments = departmentRepository.findByLocation(location);
        return departments.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    //Use the countByLocation in the repository to count the number of departments in a location
    public Long countByLocation(String location){
        List<Object[]> results = departmentRepository.countByLocation(location);
        return results.stream().mapToLong(result -> (Long) result[0]).sum();
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

