package com.example.Company.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Company.entity.DepartmentLocation;

@Repository
public interface DepartmentLocationRepository extends JpaRepository<DepartmentLocation, Long> {
}
