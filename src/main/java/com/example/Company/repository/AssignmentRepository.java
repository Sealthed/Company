package com.example.Company.repository;

import com.example.Company.entity.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
    List<Assignment> findByNumberOfHoursNot(int numberOfHours);

    //Add a simple @Query
    @Query("SELECT a FROM Assignment a WHERE a.numberOfHours > 10")
    List<Assignment> findAssignmentByNumberOfHoursGreaterThan10();

    //Add a normal @Query with uses join for 2 table
    @Query("SELECT a FROM Assignment a JOIN a.employee e WHERE e.id = ?1")
    List<Assignment> findAssignmentByEmployeeId(Long id);

    //Add a complex @Query with uses join for 3 table
    @Query("SELECT a FROM Assignment a JOIN a.employee e JOIN a.project p WHERE e.id = ?1 AND p.id = ?2")
    List<Assignment> findAssignmentByEmployeeIdAndProjectId(Long employeeId, Long projectId);


}

