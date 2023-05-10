package com.example.Company.service;

import com.example.Company.repository.RelativesRepository;
import com.example.Company.serviceDTO.RelativesDTO;
import com.example.Company.entity.Relatives;
import com.example.Company.Mapper.RelativesMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RelativesService {
    @Autowired
    private RelativesRepository relativesRepository;
    private RelativesMapper relativesMapper;

    public List<RelativesDTO> getAllRelatives() {
        List<Relatives> relatives = relativesRepository.findAll();
        return relatives.stream()
                .map(relativesMapper::toDTO)
                .collect(Collectors.toList());
    }

    public RelativesDTO getRelativesById(Long id) {
        Relatives relatives = relativesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Relatives not found"));
        return relativesMapper.toDTO(relatives);
    }

    public RelativesDTO createRelatives(RelativesDTO relativesDTO) {
        Relatives relatives = new Relatives();
        relatives.setId(relativesDTO.getId());
        relatives.setFullName(relativesDTO.getRelativename());
        Relatives savedRelatives = relativesRepository.save(relatives);
        return relativesMapper.toDTO(savedRelatives);
    }

    public void deleteRelativesById(Long id) {
        relativesRepository.deleteById(id);
    }



    //Add method that uses NOT that filter out all relatives that have a specific name "Abby"
    public List<RelativesDTO> findRelativesByNameNot(String name) {
        List<Relatives> relatives = relativesRepository.findByFullNameNot(name);
        return relatives.stream()
                .map(relativesMapper::toDTO)
                .collect(java.util.stream.Collectors.toList());
    }


    public RelativesDTO updateRelatives(Long id, RelativesDTO relativesDTO) {
        Relatives relatives = relativesRepository.findById(id).orElseThrow(() -> new RuntimeException("Relatives not found with id: " + id));
        relatives.setFullName(relativesDTO.getRelativename());
        Relatives updatedRelatives = relativesRepository.save(relatives);
        return relativesMapper.toDTO(updatedRelatives);
    }

}
