package com.exalt.villaRentalSystem.repository;

import com.exalt.villaRentalSystem.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository <T extends Person> extends JpaRepository<T,Integer> {

}
