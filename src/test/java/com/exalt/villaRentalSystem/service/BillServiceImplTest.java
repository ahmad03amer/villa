package com.exalt.villaRentalSystem.service;

import com.exalt.villaRentalSystem.model.Bill;
import com.exalt.villaRentalSystem.model.Customer;
import com.exalt.villaRentalSystem.repository.BillRepository;
import com.exalt.villaRentalSystem.repository.CustomerRepository;
import javassist.NotFoundException;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
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


    public void createBill(){
        Bill bill = new Bill();
        bill.setAmount(3200);
        bill.setDescription("online");
        bill.setDate(new Date());
        bill.setVillaId(1);
        bill.setCustomer(customerRepository.findById(1).get());
        billRepository.save(bill);
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
    }

    @Test
    public void addBillAsJsonInputTest() throws JSONException, NotFoundException {
        String addBillUrl = "http://localhost:8084/api/v1/bill";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        JSONObject billJsonObject = new JSONObject();
        billJsonObject.put("amount",2200);
        billJsonObject.put("description","online");
        billJsonObject.put("villaId",1);
        billJsonObject.put("customer",customerRepository.findById(1).get());

        HttpEntity<String> request = new HttpEntity<String>(billJsonObject.toString(), headers);
        Bill bill = restTemplate.postForObject(addBillUrl, request, Bill.class);

        if (bill != null){
            assertAll("villa",
                    () -> assertEquals(2200,bill.getAmount()),
                    () -> assertEquals("online",bill.getDescription()),
                    () -> assertEquals(1,bill.getVillaId())
            );
        }else {
            throw new NotFoundException("The villa with id=2 does not exist");
        }
        assertNotNull(bill,"villa Object is null!!");
    }
}
