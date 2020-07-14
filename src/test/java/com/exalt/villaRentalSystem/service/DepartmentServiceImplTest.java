package com.exalt.villaRentalSystem.service;

import com.exalt.villaRentalSystem.model.Department;
import com.exalt.villaRentalSystem.repository.DepartmentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
}
