package com.example.Company.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Company.entity.Department;


import java.time.LocalDate;
import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    List<Department> findByStartDate(LocalDate startDate);
    List<Department> findByLocation(String location);
    int countByLocation(String location);

}

