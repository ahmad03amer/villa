package com.exalt.villaRentalSystem.service.impl;

import com.exalt.villaRentalSystem.converter.EmployeeConverter;
import com.exalt.villaRentalSystem.dto.EmployeeDto;
import com.exalt.villaRentalSystem.dto.Mapper.EmployeeMapper;
import com.exalt.villaRentalSystem.errorAPI.InputNotValid;
import com.exalt.villaRentalSystem.errorAPI.NotFoundExceptions;
import com.exalt.villaRentalSystem.model.Employee;
import com.exalt.villaRentalSystem.projection.EmployeeProjection;
import com.exalt.villaRentalSystem.repository.EmployeeRepository;
import com.exalt.villaRentalSystem.service.EmployeeService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.PostConstruct;
import java.util.List;

@Log
@Service
public  class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    EmployeeConverter employeeConverter;


    public  Employee add(Employee employee) {
        if(employee != null){
            employeeRepository.save(employee);
            return employee;
        }else {
            throw new NotFoundExceptions("Couldn't add a null employee - ");
        }
    }

    public EmployeeDto update( EmployeeDto employeeDto,int id) {
        if(employeeRepository.existsById(id)){
            EmployeeDto updatedEmployee =EmployeeMapper.INSTANCE.employeeToDto(employeeRepository.findById(id).get());
            updatedEmployee.setFullName(employeeDto.getFullName());
            updatedEmployee.setIdNumber(employeeDto.getIdNumber());
            updatedEmployee.setEmail(employeeDto.getEmail());
            updatedEmployee.setPassword(employeeDto.getPassword());
            updatedEmployee.setPhoneNumber(employeeDto.getPhoneNumber());
            updatedEmployee.setHours(employeeDto.getHours());
            updatedEmployee.setDOB(employeeDto.getDOB());
            updatedEmployee.setRole(employeeDto.getRole());
            updatedEmployee.setGender(employeeDto.getGender());
            updatedEmployee.setSalary(employeeDto.getSalary());
            employeeRepository.save(EmployeeMapper.INSTANCE.dtoToEmployee(updatedEmployee));
            return updatedEmployee;
        }else {
            throw new NotFoundExceptions("There is no employee with the given id- "+id);
        }

    }

    public void delete(int id) {
        if (id < 0)
            throw new InputNotValid("UnValid input id , it must be positive integer");
        if(!employeeRepository.existsById(id))
            throw new NotFoundExceptions("Fail tp delete the object Employee with id - "+id);
        employeeRepository.deleteById(id);
    }

    public List<Employee> filterEmployeeByAddress(@RequestParam String address){
        List<Employee> employees =employeeRepository.filterEmployeeByAddress(address);
        return employees;
    }


    public List<EmployeeProjection> findAllProjectedBy(){
        return employeeRepository.findAllProjectedBy();
    }

    @Override
    public Page<Employee> loadEmployeesPaging(int page, int size) {
        Pageable pagable =  PageRequest.of(page,size, Sort.Direction.DESC ,"fullName");
        Page<Employee> employees = employeeRepository.findAll(pagable);
        return employees;
    }


    public List<EmployeeDto> findAll(){
        List<Employee> employees = employeeRepository.findAll();
        return EmployeeMapper.INSTANCE.employeesToDtos(employees);
    }

    public EmployeeDto findById(int id){
        if (id < 0)
            throw new InputNotValid("UnValid input id , it must be positive integer");

           Employee employee =employeeRepository.findById(id).orElse(null);

        if(employee != null)
            return EmployeeMapper.INSTANCE.employeeToDto(employee);
        else
            throw new NotFoundExceptions("There is no Employee with id - "+id);
    }

    public EmployeeDto save(EmployeeDto emp){
        if(emp != null){
            Employee employee = EmployeeMapper.INSTANCE.dtoToEmployee(emp);
            employee = employeeRepository.save(employee);
            return EmployeeMapper.INSTANCE.employeeToDto(employee);
        }else {
            throw new InputNotValid("can't add a null employee ");
        }
    }
}
