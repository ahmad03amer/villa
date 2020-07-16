package com.exalt.villaRentalSystem.service.impl;

import com.exalt.villaRentalSystem.dto.DepartmentDto;
import com.exalt.villaRentalSystem.dto.EmployeeDto;
import com.exalt.villaRentalSystem.dto.Mapper.DepartmentMapper;
import com.exalt.villaRentalSystem.dto.Mapper.EmployeeMapper;
import com.exalt.villaRentalSystem.errorAPI.InputNotValid;
import com.exalt.villaRentalSystem.errorAPI.NotFoundExceptions;
import com.exalt.villaRentalSystem.model.Department;
import com.exalt.villaRentalSystem.model.Employee;
import com.exalt.villaRentalSystem.repository.DepartmentRepository;
import com.exalt.villaRentalSystem.repository.EmployeeRepository;
import com.exalt.villaRentalSystem.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<DepartmentDto> findAll(){
      List<Department> departments = departmentRepository.findAll();
      return DepartmentMapper.INSTANCE.departmentsToDtos(departments);
  }

    public DepartmentDto findById(int id){
        if (id < 0)
            throw new InputNotValid("UnValid input id , it must be positive integer");
        Department department = departmentRepository.findById(id).orElse(null);

        if(department != null)
            return DepartmentMapper.INSTANCE.departmentToDto(department);
        else
            throw new NotFoundExceptions("There is no Department with id - "+id);
    }

    public DepartmentDto add(DepartmentDto dep){
        Department department = DepartmentMapper.INSTANCE.dtoToDepartment(dep);
        department = departmentRepository.save(department);
        return DepartmentMapper.INSTANCE.departmentToDto(department);
    }

    public void delete(int id) {
        if (id < 0)
            throw new InputNotValid("UnValid input id , it must be positive integer");
        if(!departmentRepository.existsById(id))
            throw new NotFoundExceptions("Fail tp delete the object Department with id - "+id);
        departmentRepository.deleteById(id);
    }

    public void addEmployee(List<EmployeeDto> employeeDtos, int id){
        if (id < 0)
            throw new InputNotValid("UnValid input id , it must be positive integer");
        if(!departmentRepository.existsById(id)){
            throw new NotFoundExceptions("there is no department with id - "+id);
        }else {
            Department department = departmentRepository.findById(id).get();
            List<Employee> employees = EmployeeMapper.INSTANCE.dtosToEmployees(employeeDtos);
            employees.forEach(employee -> employee.setDepartment(department));
            employees.forEach(employee ->  employeeRepository.save(employee));
        }
    }

    public List<Employee> getEmployees(int id){
        if (id < 0)
            throw new InputNotValid("UnValid input id , it must be positive integer");

        if(!departmentRepository.existsById(id)) {
            throw new NotFoundExceptions("there is no department with id - "+id);
        }else{
            Department department = departmentRepository.findById(id).get();
            List<Employee> employees = employeeRepository.getAllEmployeesInDepartment(id);
            return employees;
        }
    }
}

