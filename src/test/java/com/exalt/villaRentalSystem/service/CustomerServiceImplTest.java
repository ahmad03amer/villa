package com.exalt.villaRentalSystem.service;

import com.exalt.villaRentalSystem.errorAPI.NotFoundExceptions;
import com.exalt.villaRentalSystem.model.Customer;
import com.exalt.villaRentalSystem.model.Villa;
import com.exalt.villaRentalSystem.repository.CustomerRepository;
import com.exalt.villaRentalSystem.repository.VillaRepository;
import javassist.NotFoundException;
import lombok.extern.java.Log;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Log
@SpringBootTest
class CustomerServiceImplTest {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private VillaRepository villaRepository;

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

    @BeforeEach
    public void createCustomer(){
        Customer customer = new Customer();
        customer.setId(1);
        customer.setIdNumber("3669665");
        customer.setEmail("raed@gmail.com");
        customer.setFullName("raed");
        customer.setRole("customer");
        customer.setPassword("@366952");
        customer.setDOB("1978-05-12");
        customer.setGender("male");
        customer.setPhoneNumber("+9705366695");
        customer.setAddress("nablus");

        customerRepository.save(customer);
        createVilla(customer);
    }

    public void createVilla(Customer c){
        Villa villa = new Villa();
        villa.setId(2);
        villa.setName("yafa");
        villa.setAddress("jerusalem");
        villa.setCost(3200);
        villa.setStatus(true);
        villa.setDescription("Nice");
        villa.setCustomer(c);
        villaRepository.save(villa);
    }

    @Test
    public void findAllCustomersTest(){
        List<Customer> customers = customerRepository.findAll();
        Assert.assertNotNull(customers);
        customers.forEach(customer -> System.out.println(customer.getFullName()));
    }

    @Test
    public void findByIdTest() throws NotFoundException {
        Customer customer =  customerRepository.findById(1).get();
        Assert.assertTrue("employee does not exist",customer != null);
        if(customer != null){
            assertAll("employee",
                    () -> assertEquals("customer",customer.getRole()),
                    () -> assertEquals(1,customer.getId()),
                    () -> assertEquals("nablus",customer.getAddress()),
                    () -> assertEquals("1978-05-12",customer.getDOB()),
                    () -> assertEquals("raed",customer.getFullName()),
                    () -> assertEquals("male",customer.getGender()),
                    () -> assertEquals("raed@gmail.com",customer.getEmail()),
                    () -> assertEquals("3669665",customer.getIdNumber()),
                    () -> assertEquals("@366952",customer.getPassword()),
                    () -> assertEquals("+9705366695",customer.getPhoneNumber())
            );
        }else {
            throw new NotFoundException("The villa with id=2 does not exist");
        }
    }

    @Test
    public void addVillasToAcustomerTest(){
        Customer customer = customerRepository.findById(1).orElse(null);
        if(customer == null)
            throw new NotFoundExceptions("There is no customer with id -1 ");
        Villa villa =villaRepository.findById(2).orElse(null);
          if(villa == null)
              throw new NotFoundExceptions("There is no villa with id -2 ");
        villa.setCustomer(customer);
        villaRepository.save(villa);
        System.out.println(customer.getId());
        Assert.assertEquals(1,villa.getCustomer().getId());
    }

    @Test
    public void getCustomerVillas(){
        Customer customer = customerRepository.findById(1).orElse(null);
        if(customer == null)
            throw new NotFoundExceptions("There is no customer with id -1 ");
        List<Villa> villas = customer.getVillas();
        if(villas == null)
            throw new NotFoundExceptions("the customer does not have any villas");
        customer.getVillas().forEach(villa -> System.out.println("Villa name ---->"+villa.getName()));
    }

    @Test
    public void displayVillaDetails(){
        Villa villa =villaRepository.findById(2).orElse(null);
        if(villa == null)
            throw new NotFoundExceptions("There is no villa with id -2 ");
    }
}