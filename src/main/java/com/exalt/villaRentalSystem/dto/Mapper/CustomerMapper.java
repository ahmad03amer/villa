package com.exalt.villaRentalSystem.dto.Mapper;

import com.exalt.villaRentalSystem.dto.CustomerDto;
import com.exalt.villaRentalSystem.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper( CustomerMapper.class );

    CustomerDto customerToDto(Customer customer);
    Customer dtoToCustomer(CustomerDto customerDto);
    List<CustomerDto> customersToDtos(List<Customer> customers);
    List<Customer> dtosTocustomers(List<CustomerDto> dto);
}
