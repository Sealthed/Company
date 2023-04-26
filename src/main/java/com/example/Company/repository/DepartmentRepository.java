package com.example.Company.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Company.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
}

