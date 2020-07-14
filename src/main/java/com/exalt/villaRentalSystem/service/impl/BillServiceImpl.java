package com.exalt.villaRentalSystem.service.impl;

import com.exalt.villaRentalSystem.converter.BillConverter;
import com.exalt.villaRentalSystem.dto.BillDto;
import com.exalt.villaRentalSystem.dto.CustomerDto;
import com.exalt.villaRentalSystem.dto.Mapper.BillMapper;
import com.exalt.villaRentalSystem.errorAPI.InputNotValid;
import com.exalt.villaRentalSystem.errorAPI.NotFoundExceptions;
import com.exalt.villaRentalSystem.model.Bill;
import com.exalt.villaRentalSystem.projection.BillProjection;
import com.exalt.villaRentalSystem.repository.BillRepository;
import com.exalt.villaRentalSystem.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillServiceImpl implements BillService {

    @Autowired
    BillRepository billRepository;
    @Autowired
    BillConverter billConverter;


    public List<BillProjection> findAllProjectedBy(){
        return billRepository.findAllProjectedBy();
    }

    public Page<Bill> loadBillsPaging(int page, int size) {
        Pageable pagable =  PageRequest.of(page,size, Sort.Direction.ASC,"amount");
        Page<Bill> bills = billRepository.findAll(pagable);
        return bills;
    }

    public List<BillDto> findAll(){
        List<Bill> bills = billRepository.findAll();
        return billConverter.entityToDto(bills);
    }


    public void delete(int id) {
        if (id < 0)
            throw new InputNotValid("UnValid input id , it must be positive integer");
        if(!billRepository.existsById(id))
            throw new NotFoundExceptions("Fail tp delete the object Bill with id - "+id);
        billRepository.deleteById(id);
    }

    public BillDto save(BillDto billDto){
        if(billDto != null) {
            Bill bill = BillMapper.INSTANCE.dtoTobill(billDto);
            bill = billRepository.save(bill);
            return BillMapper.INSTANCE.billToDto(bill);
        }else{
            throw new InputNotValid("can't add a null bill ");
        }
    }
}
