package com.exalt.villaRentalSystem.repository;

import com.exalt.villaRentalSystem.model.Employee;
import com.exalt.villaRentalSystem.projection.EmployeeProjection;
import com.exalt.villaRentalSystem.projection.VillaProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

    @Query(value="SELECT * FROM employees e WHERE e.address LIKE %:address%",nativeQuery=true)
    List<Employee> filterEmployeeByAddress(@Param("address") String address);

    @Query(value="SELECT  * FROM employees  JOIN departments  ON employees.department_id=:id",nativeQuery=true)
    List<Employee> getAllEmployeesInDepartment(@Param("id") int id);

    List<EmployeeProjection> findAllProjectedBy();

}
