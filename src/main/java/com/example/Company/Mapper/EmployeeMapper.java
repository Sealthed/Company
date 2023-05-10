package com.example.Company.Mapper;

import com.example.Company.entity.Employee;
import com.example.Company.serviceDTO.EmployeeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    @Mapping(source = "department.departmentid", target = "departmentId")
    EmployeeDTO toDTO(Employee employee);

}
