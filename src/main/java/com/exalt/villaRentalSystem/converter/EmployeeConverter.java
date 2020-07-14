package com.exalt.villaRentalSystem.converter;

import com.exalt.villaRentalSystem.dto.EmployeeDto;
import com.exalt.villaRentalSystem.model.Employee;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmployeeConverter {
    public EmployeeDto entityToDto(Employee employee) {
        EmployeeDto dto = new EmployeeDto();
        dto.setFullName(employee.getFullName());
        dto.setAddress(employee.getAddress());
        dto.setEmail(employee.getEmail());
        dto.setGender(employee.getGender());
        dto.setIdNumber(employee.getIdNumber());
        dto.setPassword(employee.getPassword());
        dto.setDOB(employee.getDOB());
        dto.setPhoneNumber(employee.getPhoneNumber());
       dto.setRole(employee.getRole());
       dto.setSalary(employee.getSalary());
       dto.setHours(employee.getHours());
        return dto;
    }

    public List<EmployeeDto> entityToDto(List<Employee> customer) {
        return	customer.stream().map(e -> entityToDto(e)).collect(Collectors.toList());
    }

    public Employee dtoToEntity(EmployeeDto dto)
    {
        Employee employee = new Employee();
        employee.setFullName(dto.getFullName());
        employee.setAddress(dto.getAddress());
        employee.setEmail(dto.getEmail());
        employee.setGender(dto.getGender());
        employee.setIdNumber(dto.getIdNumber());
        employee.setPassword(dto.getPassword());
        employee.setDOB(dto.getDOB());
        employee.setRole(dto.getRole());
        employee.setPhoneNumber(dto.getPhoneNumber());
        employee.setSalary(dto.getSalary());
        employee.setHours(dto.getHours());
        return employee;
    }

    public List<Employee> dtoToEntity(List<EmployeeDto> dto)
    {
        return dto.stream().map(e -> dtoToEntity(e)).collect(Collectors.toList());
    }

}
