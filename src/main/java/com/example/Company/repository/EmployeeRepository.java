package com.example.Company.repository;


import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Company.entity.Employee;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Object> findByFirstNameAndLastName(String firstName, String lastName);

    List<Employee> findByFirstNameLike(String firstName);
}
