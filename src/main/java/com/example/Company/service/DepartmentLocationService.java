package com.example.Company.service;

import com.example.Company.entity.DepartmentLocation;
import com.example.Company.repository.DepartmentLocationRepository;
import com.example.Company.serviceDTO.DepartmentLocationDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;

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

    //Add method that delete all department locations
    public void deleteAllDepartmentLocations() {
        departmentLocationRepository.deleteAll();
    }

    public void deleteDepartmentLocationById(Long id) {
        departmentLocationRepository.deleteById(id);
    }

    public DepartmentLocationDTO updateDepartmentLocation(Long id, DepartmentLocationDTO departmentLocationDTO) {
        DepartmentLocation departmentLocation = departmentLocationRepository.findById(id).orElseThrow(() -> new RuntimeException("DepartmentLocation not found with id: " + id));
        departmentLocation.setLocation(departmentLocationDTO.getLocation());
        DepartmentLocation updatedDepartmentLocation = departmentLocationRepository.save(departmentLocation);
        return convertToDTO(updatedDepartmentLocation);
    }

    //find department location by location id using Sort
    public List<DepartmentLocationDTO> getDepartmentLocationByLocationId(String location) {
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        List <DepartmentLocation> departmentLocations = departmentLocationRepository.findByLocationOrderByIdAsc(location, sort);
        return departmentLocations.stream()
                .map(this::convertToDTO)
                .collect(java.util.stream.Collectors.toList());
    }


    private DepartmentLocationDTO convertToDTO(DepartmentLocation departmentLocation) {
        DepartmentLocationDTO dto = new DepartmentLocationDTO();
        dto.setId(departmentLocation.getId());
        dto.setLocation(departmentLocation.getLocation());
        return dto;
    }


}
