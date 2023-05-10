package com.example.Company.rest;

import com.example.Company.serviceDTO.DepartmentDTO;
import com.example.Company.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.sql.Date;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public ResponseEntity<List<DepartmentDTO>> getAllDepartments() {
        List<DepartmentDTO> departments = departmentService.getAllDepartments();
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDTO> getDepartmentById(@PathVariable Long id) {
        DepartmentDTO department = departmentService.getDepartmentById(id);
        return new ResponseEntity<>(department, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DepartmentDTO> createDepartment(@RequestBody DepartmentDTO departmentDTO) {
        DepartmentDTO createdDepartment = departmentService.createDepartment(departmentDTO);
        return new ResponseEntity<>(createdDepartment, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartmentById(@PathVariable Long id) {
        departmentService.deleteDepartmentById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartmentDTO> updateDepartment(@PathVariable Long id, @RequestBody DepartmentDTO departmentDTO) {
        DepartmentDTO updatedDepartment = departmentService.updateDepartment(id, departmentDTO);
        return new ResponseEntity<>(updatedDepartment, HttpStatus.OK);
    }

    //Custom APIs that find departments by start date
    //Test case: http://localhost:8080/departments/from?startDate=2020-01-01
    @GetMapping("/from")
    public ResponseEntity<List<DepartmentDTO>> findDepartmentsByStartDate(
            @RequestParam("startDate")  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDateStr)
    {
        List<DepartmentDTO> departments = departmentService.findByStartDate(startDateStr);
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    //Custom APIs that find departments by location
    //Test case: http://localhost:8080/departments/location?location=London
    @GetMapping("/location")
    public ResponseEntity<List<DepartmentDTO>> findDepartmentsByLocation(
            @RequestParam("location") String location)
    {
        List<DepartmentDTO> departments = departmentService.findByLocation(location);
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    //Custom APIs that count departments by location
    @GetMapping("/countByLocation") //Test case: http://localhost:8080/departments/countByLocation?location=Indonesia
    public ResponseEntity<Long> countDepartmentsByLocation(@RequestParam("location") String location)
    {
        Long departments = departmentService.countByLocation(location);
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

}

