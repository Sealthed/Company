package com.example.Company.rest;


import com.example.Company.service.AssignmentService;
import com.example.Company.serviceDTO.AssignmentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/assignments")
public class AssignmentController {
    @Autowired
    private AssignmentService assignmentService;

    @GetMapping
    public ResponseEntity<List<AssignmentDTO>> getAllAssignments() {
        List<AssignmentDTO> assignments = assignmentService.getAllAssignments();
        return new ResponseEntity<>(assignments, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssignmentDTO> getAssignmentById(Long id) {
        AssignmentDTO assignment = assignmentService.getAssignmentById(id);
        return new ResponseEntity<>(assignment, HttpStatus.OK);
    }

    @PutMapping ("/{id}")
    public ResponseEntity<AssignmentDTO> updateAssignment(@PathVariable Long id, @RequestBody AssignmentDTO assignmentDTO) {
        AssignmentDTO updatedAssignment = assignmentService.updateAssignment(id, assignmentDTO);
        return new ResponseEntity<>(updatedAssignment, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AssignmentDTO> createAssignment(AssignmentDTO assignmentDTO) {
        AssignmentDTO createdAssignment = assignmentService.createAssignment(assignmentDTO);
        return new ResponseEntity<>(createdAssignment, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAssignmentById(@PathVariable Long id) {
        assignmentService.deleteAssignmentById(id);
        return ResponseEntity.noContent().build();
    }

}
