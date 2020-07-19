package com.exalt.villaRentalSystem.controller;
import com.exalt.villaRentalSystem.dto.EmployeeDto;
import com.exalt.villaRentalSystem.model.Employee;
import com.exalt.villaRentalSystem.projection.EmployeeProjection;
import com.exalt.villaRentalSystem.service.impl.EmployeeServiceImpl;
import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Log
@RestController
public class EmployeeController {

    @Autowired
    private EmployeeServiceImpl employeeService;

    @GetMapping("/api/v1/employees")
    public List<EmployeeDto> findAll(){
        log.info("User  entered /employees to retreive all employees ");
        List<EmployeeDto> employees = employeeService.findAll();
        return employees;
    }

    @GetMapping("/api/v1/employees/{id}")
    public EmployeeDto findById(@PathVariable int id){
        log.info("User  entered /employees/id to return an employee ");
        EmployeeDto employee = employeeService.findById(id);
        return employee;
    }

    @PostMapping("/api/v1/employee/")
    public EmployeeDto save(@RequestBody EmployeeDto emp){
        log.info("user entered the saveEmployee");
        EmployeeDto employee = employeeService.save(emp);
        return employee;
    }

    @PostMapping("/api/v1/employees/")
    public Employee  add(@RequestBody  Employee employee){
        log.info("User  entered /employees to addEmployees ");
        employeeService.add(employee);
        return employee;
    }

    @PutMapping(value = "/api/v1/employee/{id}")
    public EmployeeDto update(@RequestBody EmployeeDto employeeDto,@PathVariable int id){
        log.info("User  entered /employees/{id}/ to updateEmployee ");
        employeeService.update(employeeDto,id);
        return  employeeDto;
    }


    @DeleteMapping(value = "/api/v1/employee/{id}")
    public void delete( @PathVariable int id){
        log.info("User  entered /employees/{id}/ to deleteEmployee ");
        employeeService.delete(id);
    }

    @GetMapping("/api/v1/employees/filter")
    @ResponseBody
    public List<Employee> filterEmployeeByAddress(@RequestParam String address){
        log.info("User  entered employees/filter to search for address ");
        List<Employee> employees =  employeeService.filterEmployeeByAddress(address);
        return employees;
    }

    @GetMapping("/api/v1/employees/projected-employee")
    public List<EmployeeProjection> findAllProjectedBy(){
        log.info("User  entered /employees to projectedEmp ");
        return employeeService.findAllProjectedBy();
    }

    @GetMapping("/api/v1/employees/paging-employee")
    @ResponseBody
    public Page<Employee> loadEmployeesPaging(@RequestParam(name = "page" , defaultValue = "0", required = false) int page
            , @RequestParam(name = "size", defaultValue = "1", required = false) int size){
        log.info("User  entered /employees to pagingEmp ");
        return employeeService.loadEmployeesPaging(page,size);
    }

}
