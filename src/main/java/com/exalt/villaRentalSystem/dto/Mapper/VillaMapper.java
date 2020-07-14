package com.exalt.villaRentalSystem.dto.Mapper;

import com.exalt.villaRentalSystem.dto.VillaDto;
import com.exalt.villaRentalSystem.model.Villa;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface  VillaMapper {
    VillaMapper INSTANCE = Mappers.getMapper( VillaMapper.class );

    VillaDto villaToDto(Villa villa);
    Villa dtoToVilla(VillaDto villaDto);
    public List<VillaDto> villasToDtos(List<Villa> villas);
    public List<Villa> dtosTovillas(List<VillaDto> dto);
}
