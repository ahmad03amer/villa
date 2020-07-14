package com.exalt.villaRentalSystem.converter;

import com.exalt.villaRentalSystem.dto.VillaDto;
import com.exalt.villaRentalSystem.model.Villa;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class VillaConverter {
    public VillaDto entityToDto(Villa villa) {
       VillaDto dto = new VillaDto();
       dto.setName(villa.getName());
       dto.setAddress(villa.getAddress());
       dto.setCost(villa.getCost());
       dto.setDescription(villa.getDescription());
       dto.setStatus(villa.isStatus());
        return dto;
    }

    public List<VillaDto> entityToDto(List<Villa> villa) {
        return	villa.stream().map(v -> entityToDto(v)).collect(Collectors.toList());
    }

    public Page<VillaDto> entityToDto(Page<Villa> villa) {
        return (Page<VillaDto>) villa.stream().map(v -> entityToDto(v)).collect(Collectors.toList());
    }



    public Villa dtoToEntity(VillaDto dto)
    {
        Villa villa = new Villa();

        villa.setName(dto.getName());
        villa.setAddress(dto.getAddress());
        villa.setCost(dto.getCost());
        villa.setDescription(dto.getDescription());
        villa.setStatus(dto.isStatus());

        return villa;
    }

    public List<Villa> dtoToEntity(List<VillaDto> dto)
    {
        return dto.stream().map(v -> dtoToEntity(v)).collect(Collectors.toList());
    }
}
