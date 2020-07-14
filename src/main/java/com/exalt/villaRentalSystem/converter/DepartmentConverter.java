package com.exalt.villaRentalSystem.converter;

import com.exalt.villaRentalSystem.dto.DepartmentDto;
import com.exalt.villaRentalSystem.model.Department;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DepartmentConverter {
    public DepartmentDto entityToDto(Department department) {
        DepartmentDto dto = new DepartmentDto();
            dto.setId(department.getId());
            dto.setDescription(department.getDescription());
        return dto;
    }

    public List<DepartmentDto> entityToDto(List<Department> department) {
        return	department.stream().map(d -> entityToDto(d)).collect(Collectors.toList());
    }

    public Department dtoToEntity(DepartmentDto dto)
    {
        Department department = new Department();
        department.setId(dto.getId());
        department.setDescription(dto.getDescription());
        return department;
    }

    public List<Department> dtoToEntity(List<DepartmentDto> dto)
    {
        return dto.stream().map(d -> dtoToEntity(d)).collect(Collectors.toList());
    }
}
