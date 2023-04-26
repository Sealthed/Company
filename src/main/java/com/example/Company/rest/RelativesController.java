package com.example.Company.rest;

import com.example.Company.service.RelativesService;
import com.example.Company.serviceDTO.RelativesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpStatus;
import java.util.List;


@RestController
@RequestMapping("/relatives")
public class RelativesController {
    @Autowired
    private RelativesService relativesService;

    @GetMapping
    public ResponseEntity<List<RelativesDTO>> getAllRelatives() {
        List<RelativesDTO> relatives = relativesService.getAllRelatives();
        return new ResponseEntity<>(relatives, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RelativesDTO> getRelativesById(Long id) {
        RelativesDTO relatives = relativesService.getRelativesById(id);
        return new ResponseEntity<>(relatives, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RelativesDTO> createRelatives(RelativesDTO relativesDTO) {
        RelativesDTO createdRelatives = relativesService.createRelatives(relativesDTO);
        return new ResponseEntity<>(createdRelatives, HttpStatus.CREATED);
    }
}
