package com.example.Company.serviceDTO;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class RelativesDTO {
    private Long id;
    private String relativename;
    private String Relationship;
}
