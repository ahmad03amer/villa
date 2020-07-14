package com.exalt.villaRentalSystem.service;

import com.exalt.villaRentalSystem.dto.DepartmentDto;
import com.exalt.villaRentalSystem.dto.EmployeeDto;
import com.exalt.villaRentalSystem.model.Employee;

import java.util.List;

public interface DepartmentService {
     List<DepartmentDto> findAll();

     DepartmentDto findById(int id);

     DepartmentDto add(DepartmentDto dep);

     void delete(int id);

    void addEmployee(List<EmployeeDto> employeeDto, int id);

    List<Employee> getEmployees(int id);
}
