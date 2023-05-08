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
    public ResponseEntity<AssignmentDTO> getAssignmentById(@PathVariable Long id) {
        AssignmentDTO assignment = assignmentService.getAssignmentById(id);
        return new ResponseEntity<>(assignment, HttpStatus.OK);
    }

    @PutMapping ("/{id}")
    public ResponseEntity<AssignmentDTO> updateAssignment(@PathVariable Long id, @RequestBody AssignmentDTO assignmentDTO) {
        AssignmentDTO updatedAssignment = assignmentService.updateAssignment(id, assignmentDTO);
        return new ResponseEntity<>(updatedAssignment, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AssignmentDTO> createAssignment(@RequestBody AssignmentDTO assignmentDTO) {
        AssignmentDTO createdAssignment = assignmentService.createAssignment(assignmentDTO);
        return new ResponseEntity<>(createdAssignment, HttpStatus.CREATED);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAssignmentById(@PathVariable Long id) {
        assignmentService.deleteAssignmentById(id);
        return ResponseEntity.noContent().build();
    }


    //Custom API for filtering out assignments by number of hours
    //This API will return all assignments that are not equal to the number of hours specified in the request
    //Example: http://localhost:8080/assignments/filter?numberOfHours=40
    @GetMapping("/filter")
    public ResponseEntity<List<AssignmentDTO>> findAssignmentNotByNumberOfHours( @RequestParam ("numberOfHours") int numberOfHours) {
        List<AssignmentDTO> assignments = assignmentService.findAssignmentByNumberOfHoursNot(numberOfHours);
        return new ResponseEntity<>(assignments, HttpStatus.OK);
    }

    //Custom API using all the new @Query methods in AssignmentRepository and in AssignmentService
    //This API will return all assignments that have more than 10 hours
    //Example: http://localhost:8080/assignments/filter2
    @GetMapping("/filter2")
    public ResponseEntity<List<AssignmentDTO>> findAssignmentByNumberOfHoursGreaterThan10() {
        List<AssignmentDTO> assignments = assignmentService.findAssignmentByNumberOfHoursGreaterThan10();
        return new ResponseEntity(assignments, HttpStatus.OK);
    }

    //Custom API using all the new @Query methods in AssignmentRepository and in AssignmentService
    //This API will return an assignment using the employee id
    //Example: http://localhost:8080/assignments/filter3?employeeId=1
    @GetMapping("/filter3")
    public ResponseEntity<List<AssignmentDTO>> findAssignmentByEmployeeId(@RequestParam ("employeeId") Long employeeId) {
        List<AssignmentDTO> assignment = assignmentService.findAssignmentByEmployeeId(employeeId);
        return new ResponseEntity(assignment, HttpStatus.OK);
    }

    //Custom API using all the new @Query methods in AssignmentRepository and in AssignmentService
    //This API will return an assignment using the project id and the employee id
    //Example: http://localhost:8080/assignments/filter4?employeeId=1&projectId=1
    @GetMapping("/filter4")
    public ResponseEntity<List<AssignmentDTO>> findAssignmentByEmployeeIdAndProjectId(@RequestParam ("employeeId") Long employeeId, @RequestParam ("projectId") Long projectId) {
        List<AssignmentDTO> assignment = assignmentService.findAssignmentByEmployeeIdAndProjectId(employeeId, projectId);
        return new ResponseEntity(assignment, HttpStatus.OK);
    }

}
