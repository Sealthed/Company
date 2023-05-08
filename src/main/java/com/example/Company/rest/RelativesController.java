package com.example.Company.rest;

import com.example.Company.service.RelativesService;
import com.example.Company.serviceDTO.RelativesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<RelativesDTO> createRelatives(@RequestBody RelativesDTO relativesDTO) {
        RelativesDTO createdRelatives = relativesService.createRelatives(relativesDTO);
        return new ResponseEntity<>(createdRelatives, HttpStatus.CREATED);
    }

    @PutMapping ("/{id}")
    public ResponseEntity<RelativesDTO> updateRelatives(@PathVariable Long id, @RequestBody RelativesDTO relativesDTO) {
        RelativesDTO updatedRelatives = relativesService.updateRelatives(id, relativesDTO);
        return new ResponseEntity<>(updatedRelatives, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRelativesById(@PathVariable Long id) {
        relativesService.deleteRelativesById(id);
        return ResponseEntity.noContent().build();
    }

    //Add custom method that uses NOT that filter out all relatives that have a specific name "Abby"
    //Test Case: http://localhost:8080/relatives/search?name=Abby
    @GetMapping("/search")
    public ResponseEntity<RelativesDTO> getRelativesByName(@RequestBody String name) {
        RelativesDTO relatives = (RelativesDTO) relativesService.findRelativesByNameNot(name);
        return new ResponseEntity<>(relatives, HttpStatus.OK);
    }
}
