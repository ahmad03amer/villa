package com.exalt.villaRentalSystem.service;

import com.exalt.villaRentalSystem.dto.BillDto;
import com.exalt.villaRentalSystem.model.Bill;
import com.exalt.villaRentalSystem.projection.BillProjection;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BillService {
      List<BillDto> findAll();

      void delete(int id);

      BillDto add(BillDto billDto);

      List<BillProjection> findAllProjectedBy();

      Page<Bill> loadBillsPaging(int page, int size);

    BillDto findById(int id);
}
