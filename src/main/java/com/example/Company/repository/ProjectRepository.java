package com.example.Company.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Company.entity.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long>{
}
