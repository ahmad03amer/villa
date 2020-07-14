package com.exalt.villaRentalSystem.converter;

import com.exalt.villaRentalSystem.dto.BillDto;
import com.exalt.villaRentalSystem.model.Bill;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BillConverter {
    public BillDto entityToDto(Bill bill) {
        BillDto dto = new BillDto();
        dto.setAmount(bill.getAmount());
        dto.setDescription(bill.getDescription());
        return dto;
    }

    public List<BillDto> entityToDto(List<Bill> bill) {
        return	bill.stream().map(b -> entityToDto(b)).collect(Collectors.toList());
    }

    public Bill dtoToEntity(BillDto dto)
    {
        Bill bill = new Bill();
        bill.setAmount(dto.getAmount());
        bill.setDescription(dto.getDescription());
        return bill;
    }

    public List<Bill> dtoToEntity(List<BillDto> dto)
    {
        return dto.stream().map(b -> dtoToEntity(b)).collect(Collectors.toList());
    }
}
