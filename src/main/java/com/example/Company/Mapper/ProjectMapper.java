package com.example.Company.Mapper;

import com.example.Company.entity.Department;
import com.example.Company.entity.Project;
import com.example.Company.serviceDTO.ProjectDTO;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    ProjectMapper INSTANCE = Mappers.getMapper(ProjectMapper.class);

    @Mapping(source = "projectid", target = "id")
    @Mapping(source = "projectName", target = "name")
    @Mapping(source = "area", target = "area")
    public abstract ProjectDTO toDTO(Project project);
    @AfterMapping
    default void setDepartmentName(@MappingTarget ProjectDTO dto, Project entity) {
        if (entity.getDepartment() != null) {
            dto.setDepartmentName(entity.getDepartment().getName());
        }
    }
}
