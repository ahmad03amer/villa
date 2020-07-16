package com.exalt.villaRentalSystem.service.impl;
import com.exalt.villaRentalSystem.dto.BillDto;
import com.exalt.villaRentalSystem.dto.CustomerDto;
import com.exalt.villaRentalSystem.dto.Mapper.BillMapper;
import com.exalt.villaRentalSystem.dto.Mapper.CustomerMapper;
import com.exalt.villaRentalSystem.dto.Mapper.VillaMapper;
import com.exalt.villaRentalSystem.dto.VillaDto;
import com.exalt.villaRentalSystem.errorAPI.InputNotValid;
import com.exalt.villaRentalSystem.errorAPI.NotFoundExceptions;
import com.exalt.villaRentalSystem.model.Bill;
import com.exalt.villaRentalSystem.model.Customer;
import com.exalt.villaRentalSystem.model.Villa;
import com.exalt.villaRentalSystem.projection.CustomerProjection;
import com.exalt.villaRentalSystem.repository.BillRepository;
import com.exalt.villaRentalSystem.repository.CustomerRepository;
import com.exalt.villaRentalSystem.repository.VillaRepository;
import com.exalt.villaRentalSystem.service.CustomerService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.*;

@Log
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private VillaRepository villaRepository;
    @Autowired
    private BillRepository billRepository;

    public CustomerDto update(CustomerDto customerDto, int id) {
        if(customerRepository.existsById(id)){
            CustomerDto updatedCustomer = CustomerMapper.INSTANCE.customerToDto(customerRepository.findById(id).get());
            updatedCustomer.setId(id);
            updatedCustomer.setFullName(customerDto.getFullName());
            updatedCustomer.setIdNumber(customerDto.getIdNumber());
            updatedCustomer.setEmail(customerDto.getEmail());
            updatedCustomer.setPassword(customerDto.getPassword());
            updatedCustomer.setPhoneNumber(customerDto.getPhoneNumber());
            updatedCustomer.setDOB(customerDto.getDOB());
            updatedCustomer.setRole(customerDto.getRole());
            updatedCustomer.setGender(customerDto.getGender());
            updatedCustomer.setAddress(customerDto.getAddress());
            customerRepository.save(CustomerMapper.INSTANCE.dtoToCustomer(updatedCustomer));
            return updatedCustomer;
        }else {
            throw new NotFoundExceptions("There is no customer with the given id- "+id);
        }

    }

    public void delete(int id) {
        if (id < 0)
            throw new InputNotValid("UnValid input id , it must be positive integer");
        if(!customerRepository.existsById(id))
            throw new NotFoundExceptions("Fail tp delete the object Customer with id - " + id);
        customerRepository.deleteById(id);
    }

    public List<Customer> filterCustomersByRole( String role){
        List<Customer> customers =  customerRepository.filterCustomersByRole(role);
        return customers;
    }


    public void addVillas(List<VillaDto> villasDto, int id){
        if(id > 0){
            if (customerRepository.existsById(id)){
                Customer customer = customerRepository.findById(id).get();
                List<Villa> villas = VillaMapper.INSTANCE.dtosTovillas(villasDto);
                villas.forEach(villa -> villa.setCustomer(customer));
                villas.forEach(villa -> villaRepository.save(villa));
            }else {
                throw new NotFoundExceptions("the customer doesn't exist ! ");
            }
        }else {
            throw new InputNotValid("Not Valid input id , it must be positive integer");
        }

    }


    public List<CustomerProjection> findAllProjectedBy(){
        return customerRepository.findAllProjectedBy();
    }

    public Page<Customer> loadEmployeesPaging(int page, int size) {
        Pageable pagable =  PageRequest.of(page,size, Sort.Direction.DESC ,"fullName");
        Page<Customer> customers = customerRepository.findAll(pagable);
        return customers;
    }


    public List<CustomerDto> findAll(){
        List<Customer> customers = customerRepository.findAll();
        return CustomerMapper.INSTANCE.customersToDtos(customers);
    }


    public CustomerDto findById(int id){
        if (id < 0)
            throw new InputNotValid("UnValid input id , it must be positive integer");
        Customer customer =customerRepository.findById(id).orElse(null);

        if(customer != null)
            return CustomerMapper.INSTANCE.customerToDto(customer);
        else
            throw new NotFoundExceptions("There is no Employee with id - "+id);
    }

    public CustomerDto save(CustomerDto cus){
        if(cus != null){
            Customer customer = CustomerMapper.INSTANCE.dtoToCustomer(cus);
            customer = customerRepository.save(customer);
            return CustomerMapper.INSTANCE.customerToDto(customer);
        }else {
            throw new InputNotValid("can't add a null customer ");
        }
    }


    public void addBills(List<BillDto> billDtos, int customerId, int villaId){
        Customer customer = customerRepository.findById(customerId).get();
        Villa villa = villaRepository.findById(villaId).get();
        List<Bill> bills = BillMapper.INSTANCE.dtosTobills(billDtos);
        bills.forEach(bill ->{
            bill.setCustomer(customer);
            bill.setVillaId(villa.getId());
        });
        bills.forEach(bill -> billRepository.save(bill));
    }

}
