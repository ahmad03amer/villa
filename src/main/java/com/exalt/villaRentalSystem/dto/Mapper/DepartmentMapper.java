package com.exalt.villaRentalSystem.dto.Mapper;


import com.exalt.villaRentalSystem.dto.DepartmentDto;
import com.exalt.villaRentalSystem.model.Department;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {
    DepartmentMapper INSTANCE = Mappers.getMapper( DepartmentMapper.class );

    DepartmentDto departmentToDto(Department department);
    Department dtoToDepartment(DepartmentDto departmentDto);
    List<DepartmentDto> departmentsToDtos(List<Department> departments);
    List<Department> dtosToDepartments(List<DepartmentDto> dto);
}
