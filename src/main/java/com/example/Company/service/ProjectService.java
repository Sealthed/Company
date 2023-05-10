package com.example.Company.service;

import com.example.Company.Mapper.ProjectMapper;
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

    private final ProjectMapper projectMapper = ProjectMapper.INSTANCE;

    public List<ProjectDTO> getAllProjects() {
        List<Project> projects = projectRepository.findAll();
        return projects.stream().map(projectMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ProjectDTO getProjectById(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        return projectMapper.toDTO(project);
    }

public ProjectDTO createProject(ProjectDTO projectDTO) {
        Project project = new Project();
        project.setProjectid(projectDTO.getId());
        project.setProjectName(projectDTO.getName());
        project.setArea(projectDTO.getArea());
        Project savedProject = projectRepository.save(project);
        return projectMapper.toDTO(savedProject);
    }

    public void deleteProjectById(Long id) {
        projectRepository.deleteById(id);
    }

    public ProjectDTO updateProject(Long id, ProjectDTO projectDTO) {
        Project project = projectRepository.findById(id).orElseThrow(() -> new RuntimeException("Project not found with id: " + id));
        project.setProjectName(projectDTO.getName());
        project.setArea(projectDTO.getArea());
        Project updatedProject = projectRepository.save(project);
        return projectMapper.toDTO(updatedProject);
    }

    //Find by name ignoring case
    public List<ProjectDTO> findProjectByNameIgnoreCase(String name) {
        List<Project> projects = projectRepository.findByProjectNameIgnoreCase(name);
        return projects.stream()
                .map(projectMapper::toDTO)
                .collect(Collectors.toList());
    }


}
