package com.example.Company.rest;

import com.example.Company.entity.Employee;
import com.example.Company.entity.Relatives;
import com.example.Company.service.EmployeeService;
import com.example.Company.serviceDTO.EmployeeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.HttpStatus;

import java.time.Month;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;
    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

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

    //Get all employees that have birth months the same with input month
    @GetMapping("/employees-by-birth-month") // http://localhost:8080/employees/employees-by-birth-month?month=1
    public List<EmployeeDTO> getEmployeeByBirthMonth(@RequestParam int month) {
        return employeeService.getEmployeeByBirthMonth(Month.of(month));
    }

    //3. Get employee and all their relatives
    @GetMapping("/with-relatives") // http://localhost:8080/employees/with-relatives
    public List<EmployeeDTO> getEmployeesWithRelatives() {
        return employeeService.getEmployeesWithRelatives();
    }

    //4.Get an employee and 1 emergency contact relatives that have type is FATHER-> MOTHER-> Any
    @GetMapping("/{employeeId}/relative-by-priority") // http://localhost:8080/employees/1/relative-by-priority
    public EmployeeDTO getEmployeeWithRelativeByPriority(@PathVariable Long employeeId) {
        return employeeService.getEmployeeWithRelativeByPriority(employeeId);
    }

    //12. List of employees who have not been assigned to any project
    @GetMapping("/employees/without-project") // http://localhost:8080/employees/employees/without-project
    public List<EmployeeDTO> getEmployeesWithoutProject() {
        return employeeService.getEmployeesWithoutProject();
    }

    //13. List of employee work in a project which has been managed by another department
    @GetMapping("/employees/different-department-projects") // http://localhost:8080/employees/employees/different-department-projects
    public List<EmployeeDTO> getEmployeesInProjectsOfOtherDepartments() {
        return employeeService.getEmployeesInProjectsOfOtherDepartments();
    }


}
