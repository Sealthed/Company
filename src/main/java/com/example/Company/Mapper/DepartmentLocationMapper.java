package com.example.Company.Mapper;

import com.example.Company.entity.DepartmentLocation;
import com.example.Company.serviceDTO.DepartmentLocationDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DepartmentLocationMapper {

    @Mapping(source = "department.departmentid", target = "departmentId")
    DepartmentLocationDTO toDTO(DepartmentLocation departmentLocation);


}

