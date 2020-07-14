package com.exalt.villaRentalSystem.converter;

import com.exalt.villaRentalSystem.dto.CustomerDto;
import com.exalt.villaRentalSystem.model.Customer;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerConverter {

    public CustomerDto entityToDto(Customer customer) {
        CustomerDto dto = new CustomerDto();
        dto.setFullName(customer.getFullName());
        dto.setAddress(customer.getAddress());
        dto.setEmail(customer.getEmail());
        dto.setGender(customer.getGender());
        dto.setIdNumber(customer.getIdNumber());
        dto.setPassword(customer.getPassword());
        dto.setDOB(customer.getDOB());
        dto.setRole(customer.getRole());
        dto.setPhoneNumber(customer.getPhoneNumber());
        return dto;
    }

    public List<CustomerDto> entityToDto(List<Customer> customer) {
        return	customer.stream().map(c -> entityToDto(c)).collect(Collectors.toList());
    }

    public Customer dtoToEntity(CustomerDto dto)
    {
        Customer customer = new Customer();
        customer.setFullName(dto.getFullName());
        customer.setAddress(dto.getAddress());
        customer.setEmail(dto.getEmail());
        customer.setGender(dto.getGender());
        customer.setIdNumber(dto.getIdNumber());
        customer.setPassword(dto.getPassword());
        customer.setDOB(dto.getDOB());
        customer.setRole(dto.getRole());
        customer.setPhoneNumber(dto.getPhoneNumber());
        return customer;
    }

    public List<Customer> dtoToEntity(List<CustomerDto> dto)
    {
        return dto.stream().map(c -> dtoToEntity(c)).collect(Collectors.toList());
    }

}
