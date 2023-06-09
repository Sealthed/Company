package com.example.Company.service;

import com.example.Company.Mapper.DepartmentMapper;

import com.example.Company.Mapper.ProjectMapper;
import com.example.Company.repository.DepartmentRepository;
import com.example.Company.repository.ProjectRepository;
import com.example.Company.serviceDTO.DepartmentDTO;
import com.example.Company.entity.Department;

import com.example.Company.serviceDTO.ProjectDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private final DepartmentMapper departmentMapper = DepartmentMapper.INSTANCE;

    public List<DepartmentDTO> getAllDepartments() {
        List<Department> departments = departmentRepository.findAll();
        return departments.stream().map(departmentMapper::toDTO).collect(Collectors.toList());
    }

    public DepartmentDTO getDepartmentById(Long id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));
        return departmentMapper.toDTO(department);
    }


    public DepartmentDTO createDepartment(DepartmentDTO departmentDTO) {
        Department department = new Department();
        department.setStartDate(departmentDTO.getStartDate());
        department.setName(departmentDTO.getName());
        Department savedDepartment = departmentRepository.save(department);
        return departmentMapper.toDTO(savedDepartment);
    }

    public void deleteDepartmentById(Long id) {
        departmentRepository.deleteById(id);
    }

    //Update the service class to use named query from repository and department entity
    public List<DepartmentDTO> findDepartmentByStartDate(LocalDate date) {
        List<Department> departments = departmentRepository.findByStartDate(date);
        return departments.stream().map(departmentMapper::toDTO)
                .collect(Collectors.toList());
    }

    //Update the service class to use named query from repository and department entity
    public List<DepartmentDTO> findByStartDate(LocalDate date){
        List<Department> departments = departmentRepository.findByStartDate(date);
        return departments.stream().map(departmentMapper::toDTO).collect(Collectors.toList());
    }

    //Update the service class to use named query from repository and department entity
    public List<DepartmentDTO> findByLocation(String location){
        List<Department> departments = departmentRepository.findByLocation(location);
        return departments.stream().map(departmentMapper::toDTO).collect(Collectors.toList());
    }

    //Use the countByLocation in the repository to count the number of departments in a location
    public Long countByLocation(String location){
        int result = departmentRepository.countByLocation(location);
        return Long.valueOf(result);
    }

    public DepartmentDTO updateDepartment(Long id, DepartmentDTO departmentDTO) {
        Department department = departmentRepository.findById(id).orElseThrow(() -> new RuntimeException("Department not found with id: " + id));
        department.setName(departmentDTO.getName());
        department.setStartDate(departmentDTO.getStartDate());
        Department updatedDepartment = departmentRepository.save(department);
        return departmentMapper.toDTO(updatedDepartment);
    }
    //List of departments and projects that they manage
    public Map<DepartmentDTO, List<ProjectDTO>> getDepartmentAndProjects() {
        List<Department> departments = departmentRepository.findAll();
        return departments.stream().collect(Collectors.toMap(departmentMapper::toDTO, department -> department.getProject().stream().map(projectMapper::toDTO).collect(Collectors.toList())));
    }

    @Transactional
    public List<DepartmentDTO> getAllDepartmentsWithProjects() {
        return departmentRepository.findAll().stream()
                .map(department -> {
                    DepartmentDTO dto = new DepartmentDTO();
                    dto.setDepartmentid(department.getDepartmentid());
                    dto.setName(department.getName());
                    if (department.getProject() != null) {
                        dto.setProjects(department.getProject().stream()
                                .map(project -> {
                                    ProjectDTO projectDto = new ProjectDTO();
                                    projectDto.setId(project.getProjectid());
                                    projectDto.setArea(project.getArea());
                                    projectDto.setName(project.getProjectName());
                                    return projectDto;
                                })
                                .collect(Collectors.toList()));
                    }
                    return dto;
                })
                .collect(Collectors.toList());
    }


}

