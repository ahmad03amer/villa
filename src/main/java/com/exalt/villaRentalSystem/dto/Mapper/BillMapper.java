package com.exalt.villaRentalSystem.dto.Mapper;

import com.exalt.villaRentalSystem.dto.BillDto;
import com.exalt.villaRentalSystem.model.Bill;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BillMapper {
    BillMapper INSTANCE = Mappers.getMapper( BillMapper.class );

    BillDto billToDto(Bill bills);
    Bill dtoTobill(BillDto billDto);
    List<BillDto> billsToDtos(List<Bill> bills);
    List<Bill> dtosTobills(List<BillDto> dtos);
}
