package com.example.Company.Mapper;

import com.example.Company.entity.Employee;
import com.example.Company.entity.Relatives;
import com.example.Company.serviceDTO.EmployeeDTO;
import com.example.Company.serviceDTO.RelativesDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    @Mapping(source = "department.departmentid", target = "departmentId")
    @Mapping(source = "relatives", target = "relatives") // Add this mapping
    EmployeeDTO toDTO(Employee employee);

    @Mapping(source = "fullName", target = "relativename") // Add this mapping
    RelativesDTO toDTO(Relatives relatives);
}
