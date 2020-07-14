package com.exalt.villaRentalSystem.service;

import com.exalt.villaRentalSystem.model.Customer;
import com.exalt.villaRentalSystem.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class CustomerServiceImplTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void updateTest(){
        Customer customer = customerRepository.findById(3).get();
        customer.setFullName("fadi");
        customerRepository.save(customer);
        assertEquals("fadi",customer.getFullName(),"there is an error while updating the salary");
    }

    @Test
    public void deleteTest(){
        Customer customer = customerRepository.findById(6).get();
        if (customerRepository.existsById(6)) {
            customerRepository.delete(customer);
        }
        assertFalse(customerRepository.existsById(6),"failed to delete object");
    }

    @Test
    public void testFindAllEmpPaging(){
        PageRequest pageable = PageRequest.of(0, 2);
        Page<Customer> customers = customerRepository.findAll(pageable);
        customers.forEach(e->System.out.println(e.getFullName()));
    }

    @Test
    public void countEmployeeTest(){
        System.out.println("\n\nTotal Records ----- >> "+customerRepository.count());

    }

    @Test
    public void testFindAllPaginAndSorting(){
        Pageable pagable =  PageRequest.of(0,3, Sort.Direction.ASC ,"fullName");
        customerRepository.findAll(pagable).forEach(c->System.out.println(c.getFullName()));
    }

    @Test
    public void testFilterCustomerByRole(){
        List<Customer> customers = customerRepository.filterCustomersByRole("owner");
        customers.forEach(c ->System.out.println(c.getFullName()));
    }

    @Test
    public void addTest(){
        Customer customer = new Customer();
        customer.setId(116);
        customer.setFullName("yara");
        customer.setRole("owner");
        customer.setGender("female");
        customer.setEmail("yara@gmail.com");
        customer.setPassword("@131333");
        customer.setAddress("westbank");
        customer.setIdNumber("13334556");
        customer.setPhoneNumber("+987624637");
        customer.setDOB("1997-03-17");
        customerRepository.save(customer);
        Customer cus = customerRepository.findById(116).get();
        assertEquals("yara",cus.getFullName(),"failed to add the object");
    }

}