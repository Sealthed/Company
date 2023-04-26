package com.example.Company.service;

import com.example.Company.repository.RelativesRepository;
import com.example.Company.serviceDTO.RelativesDTO;
import com.example.Company.entity.Relatives;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RelativesService {
    @Autowired
    private RelativesRepository relativesRepository;

    public List<RelativesDTO> getAllRelatives() {
        List<Relatives> relatives = relativesRepository.findAll();
        return relatives.stream()
                .map(this::convertToDTO)
                .collect(java.util.stream.Collectors.toList());
    }

    public RelativesDTO getRelativesById(Long id) {
        Relatives relatives = relativesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Relatives not found"));
        return convertToDTO(relatives);
    }

    public RelativesDTO createRelatives(RelativesDTO relativesDTO) {
        Relatives relatives = new Relatives();
        relatives.setId(relativesDTO.getId());
        relatives.setFullName(relativesDTO.getRelativename());
        Relatives savedRelatives = relativesRepository.save(relatives);
        return convertToDTO(savedRelatives);
    }

    private RelativesDTO convertToDTO(Relatives relatives) {
        RelativesDTO dto = new RelativesDTO();
        dto.setId(relatives.getId());
        dto.setRelativename(relatives.getFullName());
        return dto;
    }
}
