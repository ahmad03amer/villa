package com.exalt.villaRentalSystem.repository;

import com.exalt.villaRentalSystem.model.Bill;
import com.exalt.villaRentalSystem.projection.BillProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill,Integer> {
    List<BillProjection> findAllProjectedBy();

}
