package com.exalt.villaRentalSystem.controller;
import com.exalt.villaRentalSystem.dto.BillDto;
import com.exalt.villaRentalSystem.dto.CustomerDto;
import com.exalt.villaRentalSystem.dto.VillaDto;
import com.exalt.villaRentalSystem.model.Customer;
import com.exalt.villaRentalSystem.model.Villa;
import com.exalt.villaRentalSystem.projection.CustomerProjection;
import com.exalt.villaRentalSystem.service.CustomerService;
import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Log
@RestController
public class CustomerController {
    Logger log = LoggerFactory.getLogger(VillaController.class);

    @Autowired
    private CustomerService customerService ;

    @GetMapping("/api/v1/customers")
    public List<CustomerDto> findAll(){
        log.info("user entered the /customers/ to return the all customers");
        List<CustomerDto> customers = customerService.findAll();
        return customers;
    }

    @GetMapping("/api/v1/customer/{id}")
    public CustomerDto findById(@PathVariable int id){
        log.info("user entered the /customers/id to search a customer");
        CustomerDto customer = customerService.findById(id);
        return customer;
    }

    @PostMapping("/api/v1/customer/add")
    public CustomerDto save(@RequestBody CustomerDto cus){
        log.info("user entered the saveCustomer");
        CustomerDto customer = customerService.save(cus);
        return customer;
    }

    @PutMapping(value = "/api/v1/customers/{id}")
    public CustomerDto update(@RequestBody CustomerDto customer,@PathVariable int id){
        log.info("User  entered /customers/{id}/ to updateCustomers");
        customerService.update(customer,id);
        return  customer;
    }

    @DeleteMapping(value = "/api/v1/customer/delete/{id}")
    public void delete( @PathVariable int id){
        log.info("User  entered /customers/{id}/ to deleteCustomers ");
        customerService.delete(id);
    }

    @GetMapping("/api/v1/customers/filter")
    @ResponseBody
    public List<Customer> filterCustomersByRole(@RequestParam String role){
        log.info("User  entered customers/filter to search for role customers ");
        List<Customer> customers =  customerService.filterCustomersByRole(role);
        return customers;
    }

    @PostMapping("/api/v1/customer/{id}/villas")
    public void addVillas(@RequestBody  List<VillaDto> villasDto, @PathVariable int id){
        log.info("User  entered /customers to addVilla ");
        customerService.addVillas(villasDto,id);
        System.out.println(villasDto.get(0).getName());
    }

    @GetMapping("/api/v1/customers/projectedCus")
    public List<CustomerProjection> findAllProjectedBy(){
        log.info("User  entered /employees to projectedCus ");
        return customerService.findAllProjectedBy();
    }

    @GetMapping("/api/v1/customers/pagingCus")
    @ResponseBody
    public Page<Customer> loadEmployeesPaging(@RequestParam(name = "page" , defaultValue = "0", required = false) int page
            , @RequestParam(name = "size", defaultValue = "1", required = false) int size){
        log.info("User  entered /employees to pagingCus ");
        return customerService.loadEmployeesPaging(page,size);
    }

    @PostMapping("/api/v1/customer/{customerId}/villa/{villaId}/bills")
    public void addBills(@RequestBody  List<BillDto> billDtos, @PathVariable int customerId,@PathVariable int villaId){
        log.info("User  entered /customers to addBills ");
        customerService.addBills(billDtos,customerId,villaId);
        System.out.println(billDtos.get(0).getDescription());
    }


}
