package com.example.Company.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Company.entity.Department;


import java.time.LocalDate;
import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    @Query(name = "Department.findByStartDate")
    public List<Department> findByStartDate(LocalDate startDate);

    @Query(name = "Department.findByLocation")
    public List<Department> findByLocation(String location);

    @Query(name = "Department.countByLocation")
    public List<Object[]> countByLocation(@Param("location") String location);
}

