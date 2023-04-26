package com.example.Company.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Company.entity.Relatives;

@Repository
public interface RelativesRepository extends JpaRepository<Relatives, Long> {
}
