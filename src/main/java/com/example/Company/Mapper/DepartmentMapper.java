package com.example.Company.Mapper;

import com.example.Company.entity.Department;
import com.example.Company.serviceDTO.DepartmentDTO;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper (componentModel = "spring")
public interface DepartmentMapper {

    DepartmentMapper INSTANCE = Mappers.getMapper(DepartmentMapper.class);

    @Mapping(source = "departmentid", target = "departmentid")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "startDate", target = "startDate")
    DepartmentDTO toDTO(Department department);


}
