package com.example.Company.service;

import com.example.Company.entity.Assignment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.Company.repository.AssignmentRepository;
import com.example.Company.serviceDTO.AssignmentDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AssignmentService {
    @Autowired
    private AssignmentRepository assignmentRepository;

    public List<AssignmentDTO> getAllAssignments() {
        List<Assignment> assignments = assignmentRepository.findAll();
        return assignments.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public AssignmentDTO getAssignmentById(Long id) {
        Assignment assignment = assignmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Assignment not found"));
        return convertToDTO(assignment);
    }

    public AssignmentDTO createAssignment(AssignmentDTO assignmentDTO) {
        Assignment assignment = new Assignment();
        assignment.setId(assignmentDTO.getId());
        assignment.setNumberOfHours(assignmentDTO.getNumberOfHours());
        Assignment savedAssignment = assignmentRepository.save(assignment);
        return convertToDTO(savedAssignment);
    }

    public void deleteAssignmentById(Long id) {
        assignmentRepository.deleteById(id);
    }

    public AssignmentDTO updateAssignment(Long id, AssignmentDTO assignmentDTO) {
        Assignment assignment = assignmentRepository.findById(id).orElseThrow(() -> new RuntimeException("Assignment not found with id: " + id));
        assignment.setNumberOfHours(assignmentDTO.getNumberOfHours());
        Assignment updatedAssignment = assignmentRepository.save(assignment);
        return convertToDTO(updatedAssignment);
    }

    public List<AssignmentDTO> findAssignmentByNumberOfHoursNot(Integer numberOfHours) {
        List<Assignment> assignments = assignmentRepository.findByNumberOfHoursNot(numberOfHours);
        return assignments.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    //Add method that delete all assignments
    public void deleteAllAssignments() {
        assignmentRepository.deleteAll();
    }

    private AssignmentDTO convertToDTO(Assignment assignment) {
        AssignmentDTO dto = new AssignmentDTO();
        dto.setId(assignment.getId());
        dto.setNumberOfHours(assignment.getNumberOfHours());
        return dto;
    }
}
