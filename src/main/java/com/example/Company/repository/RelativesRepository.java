package com.example.Company.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Company.entity.Relatives;

import java.util.List;

@Repository
public interface RelativesRepository extends JpaRepository<Relatives, Long> {
    //Add method that uses NOT that filter out all relatives that have a specific name "Abby"
    List<Relatives> findByFullNameNot(String name);
}
