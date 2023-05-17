package com.example.Company.service;

import com.example.Company.CustomDTO.EmployeeRelativesDTO;
import com.example.Company.Mapper.EmployeeMapper;
import com.example.Company.entity.Employee;
import com.example.Company.entity.Relatives;
import com.example.Company.repository.EmployeeRepository;
import com.example.Company.serviceDTO.EmployeeDTO;
import com.example.Company.serviceDTO.RelativesDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.time.Month;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    @Autowired
    private final EmployeeRepository employeeRepository;
    @Autowired
    private final EmployeeMapper employeeMapper;

    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map(employeeMapper::toDTO)
                .collect(Collectors.toList());
    }

    public EmployeeDTO getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        return employeeMapper.toDTO(employee);
    }

    //Find employee by first name and last name
    public EmployeeDTO getEmployeeByFirstNameAndLastName(String firstName, String lastName) {
        Employee employee = (Employee) employeeRepository.findByFirstNameAndLastName(firstName, lastName)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        return employeeMapper.toDTO(employee);
    }



    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setDateOfBirth(employeeDTO.getDateOfBirth());
        employee.setFirstName(employeeDTO.getFirstName());
        employee.setLastName(employeeDTO.getLastName());
        employee.setMiddleName(employeeDTO.getMiddleName());
        employee.setGender(employeeDTO.getGender());
        employee.setSalary(employeeDTO.getSalary());
        Employee savedEmployee = employeeRepository.save(employee);
        return employeeMapper.toDTO(savedEmployee);
    }

    public void deleteEmployeeById(Long id) {
        employeeRepository.deleteById(id);
    }


    public EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
        employee.setFirstName(employeeDTO.getFirstName());
        employee.setLastName(employeeDTO.getLastName());
        employee.setGender(employeeDTO.getGender());
        Employee updatedEmployee = employeeRepository.save(employee);
        return employeeMapper.toDTO(updatedEmployee);
    }
    //Add method to find employee first name like "John" using the repository
    public List<EmployeeDTO> getEmployeeByFirstName(String firstName) {
        List<Employee> employees = employeeRepository.findByFirstNameLike(firstName);
        return employees.stream().map(employeeMapper::toDTO)
                .collect(Collectors.toList());
    }


    public List<EmployeeDTO> getEmployeesWithRelatives() {
        List<Employee> employees = employeeRepository.findAll();

        return employees.stream()
                .map(employeeMapper::toDTO)
                .collect(Collectors.toList());
    }

    public EmployeeDTO getEmployeeWithRelativeByPriority(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found"));

        RelativesDTO relative = employee.getRelatives().stream()
                .filter(relatives -> isPriorityRelationship(relatives.getRelationship()))
                .map(employeeMapper::toDTO)
                .findFirst()
                .orElse(null);

        EmployeeDTO employeeDTO = employeeMapper.toDTO(employee);
        employeeDTO.setEmerencyContact(relative);

        return employeeDTO;
    }

    private boolean isPriorityRelationship(String relationship) {
        // Define the priority relationships
        List<String> priorityRelationships = Arrays.asList("Father", "Mother");

        return priorityRelationships.contains(relationship);
    }

    //2. Get all employees that have birth months that is the same with an input month
    public List<EmployeeDTO> getEmployeeByBirthMonth(Month month) {
        List<Employee> employees = employeeRepository.findByBirthMonth(month.getValue());
        return employees.stream().map(employeeMapper::toDTO).collect(Collectors.toList());
    }

    @Transactional
    public List<EmployeeDTO> getEmployeesWithoutProject() {
        return employeeRepository.findAll().stream()
                .filter(employee -> employee.getAssignment() == null || employee.getAssignment().isEmpty())
                .map(employeeMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<EmployeeDTO> getEmployeesInProjectsOfOtherDepartments() {
        return employeeRepository.findAll().stream()
                .filter(employee -> employee.getAssignment() != null)
                .filter(employee -> employee.getAssignment().stream()
                        .anyMatch(assignment -> !assignment.getProject().getDepartment().equals(employee.getDepartment())))
                .map(employeeMapper::toDTO)
                .collect(Collectors.toList());
    }
}


