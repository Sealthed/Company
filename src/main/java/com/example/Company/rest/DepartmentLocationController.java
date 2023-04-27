package com.example.Company.rest;
import com.example.Company.entity.DepartmentLocation;
import com.example.Company.service.DepartmentLocationService;
import com.example.Company.serviceDTO.DepartmentLocationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<DepartmentLocationDTO> createDepartmentLocation(@RequestBody DepartmentLocationDTO departmentLocationDTO) {
        DepartmentLocationDTO createdDepartmentLocation = departmentLocationService.createDepartmentLocation(departmentLocationDTO);
        return new ResponseEntity<>(createdDepartmentLocation, HttpStatus.CREATED);
    }

    @PutMapping ("/{id}")
    public ResponseEntity<DepartmentLocationDTO> updateDepartmentLocation(@PathVariable Long id, @RequestBody DepartmentLocationDTO departmentLocationDTO) {
        DepartmentLocationDTO updatedDepartmentLocation = departmentLocationService.updateDepartmentLocation(id, departmentLocationDTO);
        return new ResponseEntity<>(updatedDepartmentLocation, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartmentLocationById(@PathVariable Long id) {
        departmentLocationService.deleteDepartmentLocationById(id);
        return ResponseEntity.noContent().build();
    }
    //Add method to delete all Department Locations
    //http://localhost:8080/departmentLocations/deleteAll
    @DeleteMapping("/deleteAll")
    public ResponseEntity<Void> deleteAllDepartmentLocations() {
        departmentLocationService.deleteAllDepartmentLocations();
        return ResponseEntity.noContent().build();
    }

    //Custom APIs that find Department Location Sort by Id
    //http://localhost:8080/departmentLocations/sort
    @GetMapping("/search")
    public ResponseEntity<DepartmentLocationDTO> findDepartmentLocationName( @RequestParam ("Location") String location) {
        List<DepartmentLocationDTO> departmentLocations = departmentLocationService.getDepartmentLocationByLocationId(location);
        return new ResponseEntity(departmentLocations, HttpStatus.OK);
    }
}
