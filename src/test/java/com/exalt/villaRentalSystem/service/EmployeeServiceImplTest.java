
package com.exalt.villaRentalSystem.service;
import com.exalt.villaRentalSystem.model.Employee;
import com.exalt.villaRentalSystem.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmployeeServiceImplTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void updateTest(){
        Employee employee = employeeRepository.findById(3).get();
        employee.setSalary(1500);
        employeeRepository.save(employee);
        assertEquals(1500,employee.getSalary(),"there is an error while updating the salary");
    }

    @Test
    public void deleteTest(){
        Employee employee = employeeRepository.findById(99).get();
        if (employeeRepository.existsById(99)) {
            employeeRepository.delete(employee);
        }
        assertFalse(employeeRepository.existsById(99),"failed to delete object");
    }

    @Test
    public void testFindAllEmpPaging(){
        PageRequest pageable = PageRequest.of(0, 2);
        Page<Employee> employees = employeeRepository.findAll(pageable);
        employees.forEach(e->System.out.println(e.getFullName()));
    }
    @Test
    public void testFilterEmpByAddress(){
        List<Employee> employees = employeeRepository.filterEmployeeByAddress("nablus");
        employees.forEach(e ->System.out.println("-----> "+ e.getFullName()) );
    }

    @Test
    public void countEmployeeTest(){
        System.out.println("Total Records -----> "+employeeRepository.count());
    }
}
