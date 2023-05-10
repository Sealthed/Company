package com.example.Company.Mapper;

import com.example.Company.entity.Relatives;
import com.example.Company.serviceDTO.RelativesDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface RelativesMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "fullName", target = "relativename")
    RelativesDTO toDTO (Relatives relatives);
}
