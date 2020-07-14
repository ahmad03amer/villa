package com.exalt.villaRentalSystem.service;
import com.exalt.villaRentalSystem.dto.BillDto;
import com.exalt.villaRentalSystem.dto.CustomerDto;
import com.exalt.villaRentalSystem.dto.VillaDto;
import com.exalt.villaRentalSystem.model.Customer;
import com.exalt.villaRentalSystem.projection.CustomerProjection;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface CustomerService {


     CustomerDto update( CustomerDto customerDto,int id);

     void delete(int id);

     List<Customer> filterCustomersByRole(@RequestParam String role);

     void addVillas(List<VillaDto> villasDto, int id);

     List<CustomerProjection> findAllProjectedBy();

     Page<Customer> loadEmployeesPaging(int page, int size);

     List<CustomerDto> findAll();

      CustomerDto findById(int id);

     CustomerDto save(CustomerDto cus);

     void addBills(List<BillDto> billDtos,int customerId, int villaId);
}
