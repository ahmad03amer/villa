package com.exalt.villaRentalSystem.repository;

import com.exalt.villaRentalSystem.model.Customer;
import com.exalt.villaRentalSystem.projection.CustomerProjection;
import com.exalt.villaRentalSystem.projection.EmployeeProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {

    @Query(value="SELECT * FROM customers c WHERE c.role LIKE %:role%",nativeQuery=true)
    List<Customer> filterCustomersByRole(@Param("role") String role);

    List<CustomerProjection> findAllProjectedBy();

}