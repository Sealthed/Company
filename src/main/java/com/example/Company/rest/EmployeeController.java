package com.example.Company.rest;

import com.example.Company.service.EmployeeService;
import com.example.Company.serviceDTO.EmployeeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.HttpStatus;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        List<EmployeeDTO> employees = employeeService.getAllEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id) {
        EmployeeDTO employee = employeeService.getEmployeeById(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    // Create a new employee
    // Test Case: http://localhost:8080/employees
    @PostMapping
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        EmployeeDTO createdEmployee = employeeService.createEmployee(employeeDTO);
        return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
    }

    @PutMapping ("/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable Long id, @RequestBody EmployeeDTO employeeDTO) {
        EmployeeDTO updatedEmployee = employeeService.updateEmployee(id, employeeDTO);
        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployeeById(@PathVariable Long id) {
        employeeService.deleteEmployeeById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<EmployeeDTO> getEmployeeByFirstNameAndLastName(@RequestParam String firstName,@RequestParam String lastName) {
        EmployeeDTO employee = employeeService.getEmployeeByFirstNameAndLastName(firstName, lastName);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }



    // http://localhost:8080/employees/search/firstName
    // Custom APIs find employee first name like "John"
    @GetMapping("/search/{firstName}")
    public ResponseEntity<List<EmployeeDTO>> getEmployeesByFirstName(@PathVariable String firstName) {
        List<EmployeeDTO> employees = employeeService.getEmployeeByFirstName(firstName);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

}
