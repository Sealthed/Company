package com.example.Company.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Company.entity.DepartmentLocation;

import java.util.List;

@Repository
public interface DepartmentLocationRepository extends JpaRepository<DepartmentLocation, Long> {
    List<DepartmentLocation> findByLocationOrderByIdAsc(String location, Sort sort);

    //Add some @Query here and NativeQuery = true
    @Query(value = "SELECT * FROM department_location WHERE location = ?1 ORDER BY id ASC", nativeQuery = true)
    List<DepartmentLocation> findByLocationOrderByIdAscNative(String location);

    //This query will return the locations where the total number of employees in departments at that location is greater than a specified threshold
    @Query(value = "SELECT dl.* FROM department_location dl " +
            "WHERE (SELECT COUNT(e.id) FROM employee e " +
            "JOIN department d ON e.departmentid = d.departmentid " +
            "WHERE d.department_location_id = dl.id) > 5", nativeQuery = true)
    List<DepartmentLocation> findLocationsWithEmployeeCountGreaterThanThreshold();

}
