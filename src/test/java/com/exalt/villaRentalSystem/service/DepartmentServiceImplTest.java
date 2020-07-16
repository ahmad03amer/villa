package com.exalt.villaRentalSystem.service;

import com.exalt.villaRentalSystem.errorAPI.NotFoundExceptions;
import com.exalt.villaRentalSystem.model.Department;
import com.exalt.villaRentalSystem.repository.DepartmentRepository;
import javassist.NotFoundException;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class DepartmentServiceImplTest {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Test
    public void addTest(){
        Department department = new Department();
        department.setId(112);
        department.setDescription("test");
        departmentRepository.save(department);
        Department dep = departmentRepository.findById(112).get();
        assertEquals("test",dep.getDescription(),"failed to add the object");
    }

    @Test
    public void deleteTest() {
        Department department = departmentRepository.findById(4).get();
        if (departmentRepository.existsById(4)) {
            departmentRepository.delete(department);
        }
            assertFalse(departmentRepository.existsById(4),"failed to delete object");
    }


    /**
     * the next tests for the h2 database
     */

    @BeforeEach
    public void createDepartment(){
        Department department = new Department();
        department.setId(1);
        department.setDescription("external relation");

        departmentRepository.save(department);
    }

    @Test
    public void findAllDepartmentsTest() {
        List<Department> departments = departmentRepository.findAll();
        departments.forEach(department -> System.out.println(department.getDescription()));
    }

    @Test
    public void findByIdTest(){
        Department department = departmentRepository.findById(1).get();
        Assert.assertTrue("Villa does not exist",department != null);
        assertAll("department",
                () -> assertEquals(1,department.getId()),
                () -> assertEquals("external relation",department.getDescription())
        );
    }

    @Test
    public void addDepartmentTest() throws NotFoundException {
        String addDepartmentUrl = "http://localhost:8084/api/v1/department";
        RestTemplate restTemplate = new RestTemplate();

        Department department = departmentRepository.findById(1).orElse(null);
        if (department != null){
            assertAll("department",
                    () -> assertEquals(1,department.getId()),
                    () -> assertEquals("external relation",department.getDescription())
            );
        }else {
            throw new NotFoundException("The department with id=1 does not exist");
        }

        HttpEntity<Department> request = new HttpEntity<>(department);
        ResponseEntity<Department> result = restTemplate.postForEntity(addDepartmentUrl, request, Department.class);
        Assert.assertEquals("200 OK",result.getStatusCode().toString());
    }

    @Test
    public void getDepartmentsTest(){
        String getDepartmentsUrl = "http://localhost:8084/api/v1/departments";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Department[]> response = restTemplate.getForEntity(getDepartmentsUrl, Department[].class);
        Department[] departments = response.getBody();
        if(departments == null)
            throw new NotFoundExceptions("There is no customers in the database!");
        assertNotNull(departments,"departments is null");
    }

    @Test
    public void getDepartmentTest(){
        String getDepartmentUrl = "http://localhost:8084/api/v1/department/{id}";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("X-Request-Source", "Desktop");
        HttpEntity request = new HttpEntity(headers);
        ResponseEntity<String> response = restTemplate.exchange(
                getDepartmentUrl,
                HttpMethod.GET,
                request,
                String.class,
                1
        );

        if (response.getStatusCode() == HttpStatus.OK) {
            System.out.println("Request Successful.");
            System.out.println(response.getBody());
        } else {
            System.out.println("Request Failed");
            System.out.println(response.getStatusCode());
        }
    }
}
