package com.example.Company.service;

import com.example.Company.Mapper.AssignmentMapper;
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

    private final AssignmentMapper assignmentMapper = AssignmentMapper.INSTANCE;

    public List<AssignmentDTO> getAllAssignments() {
        List<Assignment> assignments = assignmentRepository.findAll();
        return assignments.stream()
                .map(assignmentMapper::toDTO)
                .collect(Collectors.toList());
    }

    public AssignmentDTO getAssignmentById(Long id) {
        Assignment assignment = assignmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Assignment not found"));
        return assignmentMapper.toDTO(assignment);
    }

    public AssignmentDTO createAssignment(AssignmentDTO assignmentDTO) {
        Assignment assignment = assignmentMapper.convertToEntity(assignmentDTO);
        Assignment savedAssignment = assignmentRepository.save(assignment);
        return assignmentMapper.toDTO(savedAssignment);
    }

    public void deleteAssignmentById(Long id) {
        assignmentRepository.deleteById(id);
    }

    public AssignmentDTO updateAssignment(Long id, AssignmentDTO assignmentDTO) {
        Assignment assignment = assignmentRepository.findById(id).orElseThrow(() -> new RuntimeException("Assignment not found with id: " + id));
        assignment.setNumberOfHours(assignmentDTO.getNumberOfHours());
        Assignment updatedAssignment = assignmentRepository.save(assignment);
        return assignmentMapper.toDTO(updatedAssignment);
    }

    public List<AssignmentDTO> findAssignmentByNumberOfHoursNot(Integer numberOfHours) {
        List<Assignment> assignments = assignmentRepository.findByNumberOfHoursNot(numberOfHours);
        return assignments.stream()
                .map(assignmentMapper::toDTO)
                .collect(Collectors.toList());
    }

    //Update the serivce to use the new @Query in AssignmentRepository
    public List<AssignmentDTO> findAssignmentByNumberOfHoursGreaterThan10() {
        List<Assignment> assignments = assignmentRepository.findAssignmentByNumberOfHoursGreaterThan10();
        return assignments.stream()
                .map(assignmentMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<AssignmentDTO> findAssignmentByEmployeeId(Long employeeId) {
        List<Assignment> assignments = assignmentRepository.findAssignmentByEmployeeId(employeeId);
        return assignments.stream()
                .map(assignmentMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<AssignmentDTO>findAssignmentByEmployeeIdAndProjectId(Long employeeId, Long projectId) {
        List<Assignment> assignments = assignmentRepository.findAssignmentByEmployeeIdAndProjectId(employeeId, projectId);
        return assignments.stream().map(assignmentMapper::toDTO)
                .collect(Collectors.toList());
    }

}
