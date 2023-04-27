package com.example.Company.repository;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Company.entity.DepartmentLocation;

import java.util.List;

@Repository
public interface DepartmentLocationRepository extends JpaRepository<DepartmentLocation, Long> {
    List<DepartmentLocation> findByLocationOrderByIdAsc(String location, Sort sort);
}
