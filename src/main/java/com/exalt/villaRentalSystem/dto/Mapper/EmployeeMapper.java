package com.exalt.villaRentalSystem.dto.Mapper;

import com.exalt.villaRentalSystem.dto.EmployeeDto;
import com.exalt.villaRentalSystem.dto.VillaDto;
import com.exalt.villaRentalSystem.model.Employee;
import com.exalt.villaRentalSystem.model.Villa;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    EmployeeMapper INSTANCE = Mappers.getMapper( EmployeeMapper.class );

    EmployeeDto employeeToDto(Employee employee);
    Employee dtoToEmployee(EmployeeDto employeeDto);
     List<EmployeeDto> employeesToDtos(List<Employee> employees);
     List<Employee> dtosToEmployees(List<EmployeeDto> dto);
}
