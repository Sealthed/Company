package com.example.Company.rest;
import com.example.Company.entity.DepartmentLocation;
import com.example.Company.service.DepartmentLocationService;
import com.example.Company.serviceDTO.DepartmentLocationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RestController
@RequestMapping("/departmentLocations")
public class DepartmentLocationController {
    @Autowired
    private DepartmentLocationService departmentLocationService;

    @GetMapping
    public ResponseEntity<List<DepartmentLocationDTO>> getAllDepartmentLocations() {
        List<DepartmentLocationDTO> departmentLocations = departmentLocationService.getAllDepartmentLocations();
        return new ResponseEntity<>(departmentLocations, HttpStatus.OK);
    }

    @GetMapping ("/{id}")
    public ResponseEntity<DepartmentLocationDTO> getDepartmentLocationById(Long id) {
        DepartmentLocationDTO departmentLocation = departmentLocationService.getDepartmentLocationById(id);
        return new ResponseEntity<>(departmentLocation, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<DepartmentLocationDTO> createDepartmentLocation(DepartmentLocationDTO departmentLocationDTO) {
        DepartmentLocationDTO createdDepartmentLocation = departmentLocationService.createDepartmentLocation(departmentLocationDTO);
        return new ResponseEntity<>(createdDepartmentLocation, HttpStatus.CREATED);
    }
}
