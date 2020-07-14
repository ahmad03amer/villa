package com.exalt.villaRentalSystem.service;

import com.exalt.villaRentalSystem.model.Bill;
import com.exalt.villaRentalSystem.repository.BillRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class BillServiceImplTest {
    @Autowired
    private BillRepository billRepository;

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

}
