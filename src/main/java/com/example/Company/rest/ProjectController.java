package com.example.Company.rest;

import com.example.Company.service.ProjectService;
import com.example.Company.serviceDTO.ProjectDTO;
import com.example.Company.serviceDTO.ProjectDetailDTO;
import com.example.Company.serviceDTO.ProjectSalaryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.HttpStatus;
import java.util.List;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/projects")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @GetMapping
    public ResponseEntity<List<ProjectDTO>> getAllProjects() {
        List<ProjectDTO> projects = projectService.getAllProjects();
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDTO> getProjectById(Long id) {
        ProjectDTO project = projectService.getProjectById(id);
        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProjectDTO> createProject(@RequestBody ProjectDTO projectDTO) {
        ProjectDTO createdProject = projectService.createProject(projectDTO);
        return new ResponseEntity<>(createdProject, HttpStatus.CREATED);
    }

    @PutMapping ("/{id}")
    public ResponseEntity<ProjectDTO> updateProject(@PathVariable Long id, @RequestBody ProjectDTO projectDTO) {
        ProjectDTO updatedProject = projectService.updateProject(id, projectDTO);
        return new ResponseEntity<>(updatedProject, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProjectById(@PathVariable Long id) {
        projectService.deleteProjectById(id);
        return ResponseEntity.noContent().build();
    }


    //Custom API for finding projects by name (ignore case)
    //Test Case: http://localhost:8080/projects/filter?name=project
    @GetMapping("/filter")
    public ResponseEntity<List<ProjectDTO>> findProjectNameIgnoreCaseContaining(@RequestParam ("name") String name) {
        List<ProjectDTO> projects = projectService.findProjectByNameIgnoreCase(name);
        return new ResponseEntity(projects, HttpStatus.OK);
    }
    @GetMapping("/projectswithdepartmentname") //http://localhost:8080/projects/projectswithdepartmentname
    public List<ProjectDTO> getAllProjectsWithDepartmentName() {
        return projectService.getAllProjectsWithDepartmentName();
    }

    //10. List all projects with the number of employees and number of hours in a specific area
    @GetMapping("/projectswithemployeesandhours") //http://localhost:8080/projects/projectswithemployeesandhours
    public List<ProjectDetailDTO> getProjectDetailsByArea(@RequestParam ("area") String area) {
        return projectService.getProjectDetailsByArea(area);
    }

    //11. List all projects with total cost (salary) and hours in a specific area
    @GetMapping("/projects/salary") //http://localhost:8080/projects/projects/salary?area=Hanoi
    public List<ProjectSalaryDTO> getProjectSalaryDetailsByArea(@RequestParam ("area") String area) {
        return projectService.getProjectSalaryDetailsByArea(area);
    }


}
