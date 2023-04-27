package com.example.Company.service;

import com.example.Company.entity.Project;
import com.example.Company.repository.ProjectRepository;
import com.example.Company.serviceDTO.ProjectDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public List<ProjectDTO> getAllProjects() {
        List<Project> projects = projectRepository.findAll();
        return projects.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ProjectDTO getProjectById(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        return convertToDTO(project);
    }

public ProjectDTO createProject(ProjectDTO projectDTO) {
        Project project = new Project();
        project.setProjectid(projectDTO.getId());
        project.setProjectName(projectDTO.getName());
        project.setArea(projectDTO.getArea());
        Project savedProject = projectRepository.save(project);
        return convertToDTO(savedProject);
    }

    public void deleteProjectById(Long id) {
        projectRepository.deleteById(id);
    }

    //Add method that delete all projects
    public void deleteAllProjects() {
        projectRepository.deleteAll();
    }

    public ProjectDTO updateProject(Long id, ProjectDTO projectDTO) {
        Project project = projectRepository.findById(id).orElseThrow(() -> new RuntimeException("Project not found with id: " + id));
        project.setProjectName(projectDTO.getName());
        project.setArea(projectDTO.getArea());
        Project updatedProject = projectRepository.save(project);
        return convertToDTO(updatedProject);
    }

    //Find by name ignoring case
    public List<ProjectDTO> findProjectByNameIgnoreCase(String name) {
        List<Project> projects = projectRepository.findByProjectNameIgnoreCase(name);
        return projects.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ProjectDTO convertToDTO(Project project) {
        ProjectDTO dto = new ProjectDTO();
        dto.setId(project.getProjectid());
        dto.setName(project.getProjectName());
        dto.setArea(project.getArea());
        return dto;
    }

}
