
package com.exalt.villaRentalSystem.service;

import com.exalt.villaRentalSystem.errorAPI.NotFoundExceptions;
import com.exalt.villaRentalSystem.model.Employee;
import com.exalt.villaRentalSystem.repository.EmployeeRepository;
import javassist.NotFoundException;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

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

    @BeforeEach
    public void createEmployee(){
        Employee employee = new Employee();
        employee.setId(1);
        employee.setIdNumber("554645665");
        employee.setEmail("rami@gmail.com");
        employee.setFullName("rami rami");
        employee.setRole("employee");
        employee.setPassword("@356444");
        employee.setDOB("1992-05-12");
        employee.setHours(13);
        employee.setSalary(3250);
        employee.setGender("male");
        employee.setPhoneNumber("+9705336952");
        employee.setAddress("Ramallah");

        employeeRepository.save(employee);
    }

    @Test
    public void findAllEmployeesTest(){
        List<Employee> employees = employeeRepository.findAll();
        Assert.assertNotNull(employees);
    }

    @Test
    public void findByIdTest() throws NotFoundException {
        Employee employee =  employeeRepository.findById(1).get();
        Assert.assertTrue("employee does not exist",employee != null);
        if(employee != null){
            assertAll("employee",
                    () -> assertEquals("employee",employee.getRole()),
                    () -> assertEquals(1,employee.getId()),
                    () -> assertEquals("Ramallah",employee.getAddress()),
                    () -> assertEquals("1992-05-12",employee.getDOB()),
                    () -> assertEquals(3250,employee.getSalary()),
                    () -> assertEquals(13,employee.getHours()),
                    () -> assertEquals("rami rami",employee.getFullName()),
                    () -> assertEquals("male",employee.getGender()),
                    () -> assertEquals("rami@gmail.com",employee.getEmail()),
                    () -> assertEquals("554645665",employee.getIdNumber()),
                    () -> assertEquals("@356444",employee.getPassword()),
                    () -> assertEquals("+9705336952",employee.getPhoneNumber())
            );
        }else {
            throw new NotFoundException("The villa with id=2 does not exist");
        }
    }


    @Test
    public void addEmployeeTest(){
        String addEmployeeUrl = "http://localhost:8084/api/v1/employees/";
        RestTemplate restTemplate = new RestTemplate();

        Employee employee = employeeRepository.findById(1).orElse(null);
        if(employee == null){
            throw new NotFoundExceptions("The employee does not exist");
        }
        HttpEntity<Employee> request = new HttpEntity<>(employee);
        ResponseEntity<Employee> result = restTemplate.postForEntity(addEmployeeUrl, request, Employee.class);
        assertAll("employee",
                () -> assertEquals("employee",employee.getRole()),
                () -> assertEquals(1,employee.getId()),
                () -> assertEquals("Ramallah",employee.getAddress()),
                () -> assertEquals("1992-05-12",employee.getDOB()),
                () -> assertEquals(3250,employee.getSalary()),
                () -> assertEquals(13,employee.getHours()),
                () -> assertEquals("rami rami",employee.getFullName()),
                () -> assertEquals("male",employee.getGender()),
                () -> assertEquals("rami@gmail.com",employee.getEmail()),
                () -> assertEquals("554645665",employee.getIdNumber()),
                () -> assertEquals("@356444",employee.getPassword()),
                () -> assertEquals("+9705336952",employee.getPhoneNumber())
        );
        Assert.assertEquals("200 OK",result.getStatusCode().toString());
    }

    @Test
    public void getEmployeesTest(){
        String getEmployeeUrl = "http://localhost:8084/api/v1/employees/";
        RestTemplate restTemplate = new RestTemplate();

        List<Employee> employees = (List<Employee>) restTemplate.getForObject(getEmployeeUrl, Employee.class);
        assertNotNull(employees,"employee Object is null!!");
    }
}
