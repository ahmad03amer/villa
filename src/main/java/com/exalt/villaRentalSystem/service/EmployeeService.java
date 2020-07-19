package com.exalt.villaRentalSystem.service;

import com.exalt.villaRentalSystem.dto.EmployeeDto;
import com.exalt.villaRentalSystem.model.Employee;
import com.exalt.villaRentalSystem.projection.EmployeeProjection;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EmployeeService {

    Employee add(Employee employee);

    EmployeeDto update( EmployeeDto employeeDto,int id);

    void delete(int id);

    List<Employee> filterEmployeeByAddress(String address);

    List<EmployeeProjection> findAllProjectedBy();

    Page<Employee> loadEmployeesPaging(int page, int size);

    List<EmployeeDto> findAll();

    EmployeeDto findById(int id);

    EmployeeDto save(EmployeeDto emp);
}
