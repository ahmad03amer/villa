package com.exalt.villaRentalSystem.service;

import com.exalt.villaRentalSystem.dto.VillaDto;
import com.exalt.villaRentalSystem.model.Villa;
import com.exalt.villaRentalSystem.projection.VillaProjection;
import org.springframework.data.domain.Page;

import java.util.List;

public interface VillaService {

     void delete(int id);

     List<Villa> findVillaByName( String name);

     List<Villa> filterVillasByCost( double cost);

    List<VillaProjection> findAllProjectedBy();

    Page<Villa> loadVillasPaging(int page, int size);

    List<VillaDto> findAll();

    VillaDto findById(int id);


    void update(int id, VillaDto villaDto);


    void save(VillaDto villaDto);
}
