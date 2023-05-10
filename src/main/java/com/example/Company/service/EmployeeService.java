package com.example.Company.service;

import com.example.Company.Mapper.EmployeeMapper;
import com.example.Company.entity.Employee;
import com.example.Company.repository.EmployeeRepository;
import com.example.Company.serviceDTO.EmployeeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

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
}


