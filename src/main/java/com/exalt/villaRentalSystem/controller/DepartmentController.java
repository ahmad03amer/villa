package com.exalt.villaRentalSystem.controller;

import com.exalt.villaRentalSystem.dto.DepartmentDto;
import com.exalt.villaRentalSystem.dto.EmployeeDto;
import com.exalt.villaRentalSystem.model.Employee;
import com.exalt.villaRentalSystem.service.DepartmentService;
import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Log
@RestController
public class DepartmentController {
    Logger log = LoggerFactory.getLogger(VillaController.class);

    @Autowired
    private DepartmentService departmentService ;

        @GetMapping("/api/v1/departments")
    public List<DepartmentDto> findAll(){
            log.info("user entered the /departments to find all of them");
            List<DepartmentDto> departments = departmentService.findAll();
        return departments;
    }

    @GetMapping("/api/v1/department/{id}")
    public DepartmentDto findById(@PathVariable int id){
        log.info("user entered the /departments/id to find an exact department");
        DepartmentDto department = departmentService.findById(id);
        return department;
    }

    @PostMapping("/api/v1/department")
    public DepartmentDto add(@RequestBody DepartmentDto dep){
        log.info("user entered the addDepartment");
        DepartmentDto department = departmentService.add(dep);
        return department;
    }

    @DeleteMapping("/api/v1/department/delete/{id}")
    public void delete( @PathVariable int id){
        log.info("User  entered /deleteDep/{id} to deleteDepartment ");
        departmentService.delete(id);
    }

    @PostMapping("/api/v1/department/{id}/employee")
    public void addEmployee(@RequestBody List<EmployeeDto> employeeDtos , @PathVariable int id){
        log.info("User  entered department/{id}/employee to addEmployee ");
        departmentService.addEmployee(employeeDtos,id);

    }

    @GetMapping("/api/v1/department/{id}/employees")
    public List<Employee> getEmployees(@PathVariable int id){
        log.info("User  entered department/{id}/employees to return the employees in the department ");
        return departmentService.getEmployees(id);
    }
}
