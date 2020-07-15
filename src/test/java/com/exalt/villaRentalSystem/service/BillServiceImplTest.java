package com.exalt.villaRentalSystem.service;

import com.exalt.villaRentalSystem.dto.CustomerDto;
import com.exalt.villaRentalSystem.dto.Mapper.CustomerMapper;
import com.exalt.villaRentalSystem.errorAPI.NotFoundExceptions;
import com.exalt.villaRentalSystem.model.Bill;
import com.exalt.villaRentalSystem.model.Customer;
import com.exalt.villaRentalSystem.repository.BillRepository;
import com.exalt.villaRentalSystem.repository.CustomerRepository;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BillServiceImplTest {
    @Autowired
    private BillRepository billRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Test
    public void deleteTest() {
        Bill bill = billRepository.findById(5).get();
        if (billRepository.existsById(5)) {
            billRepository.delete(bill);
        }
        assertFalse(billRepository.existsById(5),"failed to delete object");
    }

    @Test
    public void testFindAllEmpPaging(){
        PageRequest pageable = PageRequest.of(0, 2);
        Page<Bill> bill = billRepository.findAll(pageable);
        bill.forEach(e->System.out.println(e.getDescription()+" > "+e.getAmount()));
    }


    @Test
    public void testFindAllPaginAndSorting(){
        Pageable pagable =  PageRequest.of(0,3, Sort.Direction.DESC ,"amount");
        billRepository.findAll(pagable).forEach(b->System.out.println(b.getAmount()));
    }

    @Test
    public void getBillTest(){
        Optional<Bill> bill = billRepository.findById(3);
        assertNotNull(bill);
    }

    @BeforeEach
    public void createBill(){
        Bill bill = new Bill();
        bill.setAmount(3200);
        bill.setDescription("online");
        bill.setDate(new Date());
        bill.setVillaId(1);
        billRepository.save(bill);
    }
/*
    @Test
    public void findallBillsTest(){
        List<Bill> bills = billRepository.findAll();
        Assert.assertNotNull(bills);
        bills.forEach(bill -> System.out.println(bill.getDescription()));
    }*/
/*

    TestRestTemplate testRestTemplate = new TestRestTemplate();
    ResponseEntity<Bill> response = testRestTemplate.getForEntity("http://localhost:8081/api/v1/bill/1", Bill.class);
*/

/*    @Test
    public void findBillRestTemplate(){
        Bill bill = billRepository.findById(1).orElse(null);
        if(bill == null)
            throw new NotFoundExceptions("There is no bill with id -1 ");

        assertEquals(bill,response);
    }*/

}
