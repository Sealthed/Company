package com.example.Company.Mapper;

import com.example.Company.entity.Project;
import com.example.Company.serviceDTO.ProjectDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProjectMapper {

    ProjectMapper INSTANCE = Mappers.getMapper(ProjectMapper.class);

    @Mapping(source = "projectid", target = "id")
    @Mapping(source = "projectName", target = "name")
    @Mapping(source = "area", target = "area")
    ProjectDTO toDTO(Project project);

}
