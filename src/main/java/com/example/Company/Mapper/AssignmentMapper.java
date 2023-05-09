package com.example.Company.Mapper;

import org.mapstruct.Mapper;
import com.example.Company.entity.Assignment;
import com.example.Company.serviceDTO.AssignmentDTO;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AssignmentMapper {
    AssignmentMapper INSTANCE = Mappers.getMapper(AssignmentMapper.class);
    AssignmentDTO toDTO(Assignment assignment);
    Assignment convertToEntity(AssignmentDTO assignmentDTO);
}
