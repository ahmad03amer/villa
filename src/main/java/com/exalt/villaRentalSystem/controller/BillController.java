package com.exalt.villaRentalSystem.controller;

import com.exalt.villaRentalSystem.dto.BillDto;
import com.exalt.villaRentalSystem.model.Bill;
import com.exalt.villaRentalSystem.projection.BillProjection;
import com.exalt.villaRentalSystem.service.BillService;
import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Log
@RestController
public class BillController {
    Logger log = LoggerFactory.getLogger(VillaController.class);

    @Autowired
    private BillService billService;


    @GetMapping("/api/v1/bills")
    public List<BillDto> findAll(){
        log.info("User  entered /dto/bills to find all bill ");
        List<BillDto> bills = billService.findAll();
        return bills;
    }

    @GetMapping("/api/v1/bill/{id}")
    public BillDto findById(@PathVariable int id){
        log.info("user entered the /bills/id to search a customer");
        BillDto bill = billService.findById(id);
        return bill;
    }
    @GetMapping("/api/v1/bills/projected-bills")
    public List<BillProjection> findAllProjectedBy(){
        log.info("User  entered /bills/projectedBills to projectedBils ");
        return billService.findAllProjectedBy();
    }

    @GetMapping("/api/v1/bills/paging-bill")
    @ResponseBody
    public Page<Bill> loadBillsPaging(@RequestParam(name = "page" , defaultValue = "0", required = false) int page
            , @RequestParam(name = "size", defaultValue = "2", required = false) int size){
        log.info("User  entered /bills/pagingBill to pagingBill ");
        return billService.loadBillsPaging(page,size);
    }

    @DeleteMapping("/api/v1/bill/{id}")
    public void delete( @PathVariable int id){
        log.info("User  entered /bill/{id} to delete bill ");
        billService.delete(id);
    }

    @PostMapping("/api/v1/bill")
    public BillDto add(@ModelAttribute BillDto billDto){
        log.info("user entered the saveBill");
        return billService.add(billDto);
    }
}
