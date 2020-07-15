package com.exalt.villaRentalSystem.service;

import com.exalt.villaRentalSystem.model.Customer;
import com.exalt.villaRentalSystem.model.Department;
import com.exalt.villaRentalSystem.model.Employee;
import com.exalt.villaRentalSystem.repository.DepartmentRepository;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

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

    @Test
    public void getDepartmentTest(){
        Optional<Department> department = departmentRepository.findById(3);
        assertNotNull(department);
    }

    /**
     * the next tests for the h2 database
     */

    @BeforeEach
    public void createDepartment(){
        Department department = new Department();
        department.setId(1);
        department.setDescription("employees");

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
                () -> assertEquals("employees",department.getDescription())
        );
    }

}
