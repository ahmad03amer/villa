package com.exalt.villaRentalSystem.repository.internalRepository;

import com.exalt.villaRentalSystem.model.Villa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VillaInRepository extends JpaRepository<Villa,Integer> {
}
