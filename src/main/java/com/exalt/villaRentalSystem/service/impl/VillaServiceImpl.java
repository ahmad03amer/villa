package com.exalt.villaRentalSystem.service.impl;


import com.exalt.villaRentalSystem.dto.Mapper.VillaMapper;
import com.exalt.villaRentalSystem.dto.VillaDto;
import com.exalt.villaRentalSystem.errorAPI.InputNotValid;
import com.exalt.villaRentalSystem.errorAPI.NotFoundExceptions;
import com.exalt.villaRentalSystem.model.Villa;
import com.exalt.villaRentalSystem.projection.VillaProjection;
import com.exalt.villaRentalSystem.repository.VillaRepository;
import com.exalt.villaRentalSystem.service.VillaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class VillaServiceImpl implements VillaService {

    @Autowired
    private VillaRepository villaRepository;

    @Transactional
    public void delete(int id) {
        if (id < 0)
            throw new InputNotValid("UnValid input id , it must be positive integer");

           if(!villaRepository.existsById(id))
               throw new NotFoundExceptions("Fail tp delete the object villa with id - "+id);
           villaRepository.deleteById(id);
    }

    public List<Villa> findVillaByName(String name){
        List<Villa> villas=villaRepository.findVillaByName(name);
        return villas;
    }

    public List<Villa> filterVillasByCost(double cost){
        if(cost < 0)
            throw new InputNotValid("input cost is not valid , it must positive double");
        List<Villa> villas = villaRepository.filterVillasByCost(cost);
        return villas;
    }

    public List<VillaProjection> findAllProjectedBy(){
        return villaRepository.findAllProjectedBy();
    }

    @Override
    public Page<Villa> loadVillasPaging(int page, int size) {
        Pageable pagable =  PageRequest.of(page,size, Sort.Direction.DESC ,"name");
        Page<Villa> villas = villaRepository.findAll(pagable);
        return villas;
    }

    public List<VillaDto> findAll(){
        List<Villa> villas = (List<Villa>) villaRepository.findAll();
        return VillaMapper.INSTANCE.villasToDtos(villas);
    }

    public VillaDto findById(int id){
        if (id < 0)
            throw new InputNotValid("UnValid input id , it must be positive integer");

        Villa villa =villaRepository.findById(id).orElse(null);
        if(villa != null)
        return VillaMapper.INSTANCE.villaToDto(villa);
        else
            throw new NotFoundExceptions("There is no villa with id - "+id);
    }

    public Villa save(VillaDto villaDto){
        if(villaDto != null){
            Villa villa = VillaMapper.INSTANCE.dtoToVilla(villaDto);
            villaRepository.save(villa);
        } else{
            throw new InputNotValid("can't add a null villa ");
        }
        return  VillaMapper.INSTANCE.dtoToVilla(villaDto);
    }

    public void update(int id, VillaDto villaDto){
        Villa villa =villaRepository.findById(id).orElse(null);
        if(villa != null){
            villa.setName(villaDto.getName());
            villa.setCost(villaDto.getCost());
            villa.setAddress(villaDto.getAddress());
            villa.setDescription(villaDto.getDescription());
            villa.setStatus(villaDto.isStatus());
            villaRepository.save(villa);
        }else {
            throw new NotFoundExceptions("villa does not exist to update!");
        }

    }


}
