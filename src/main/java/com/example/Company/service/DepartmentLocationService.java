package com.example.Company.service;

import com.example.Company.entity.DepartmentLocation;
import com.example.Company.repository.DepartmentLocationRepository;
import com.example.Company.serviceDTO.DepartmentLocationDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentLocationService {
    @Autowired
    private DepartmentLocationRepository departmentLocationRepository;

    public List<DepartmentLocationDTO> getAllDepartmentLocations() {
        List<DepartmentLocation> departmentLocations = departmentLocationRepository.findAll();
        return departmentLocations.stream()
                .map(this::convertToDTO)
                .collect(java.util.stream.Collectors.toList());
    }

    public DepartmentLocationDTO getDepartmentLocationById(Long id) {
        DepartmentLocation departmentLocation = departmentLocationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("DepartmentLocation not found"));
        return convertToDTO(departmentLocation);
    }

    public DepartmentLocationDTO createDepartmentLocation(DepartmentLocationDTO departmentLocationDTO) {
        DepartmentLocation departmentLocation = new DepartmentLocation();
        departmentLocation.setId(departmentLocationDTO.getId());
        departmentLocation.setLocation(departmentLocationDTO.getLocation());
        DepartmentLocation savedDepartmentLocation = departmentLocationRepository.save(departmentLocation);
        return convertToDTO(savedDepartmentLocation);
    }

    private DepartmentLocationDTO convertToDTO(DepartmentLocation departmentLocation) {
        DepartmentLocationDTO dto = new DepartmentLocationDTO();
        dto.setId(departmentLocation.getId());
        dto.setLocation(departmentLocation.getLocation());
        return dto;
    }
}