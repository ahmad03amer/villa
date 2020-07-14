package com.exalt.villaRentalSystem.repository;

import com.exalt.villaRentalSystem.model.Villa;
import com.exalt.villaRentalSystem.projection.VillaProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;


@Repository
public interface VillaRepository extends PagingAndSortingRepository<Villa,Integer> {
  Page<Villa> findByIdIn(List<Integer> ids , Pageable pagable);

   List<Villa> findByName(String name);

    List<Villa> findByNameAndCost(String name , double cost);

    List<Villa> findByCostGreaterThan(double name);

    List<Villa> findByDescriptionContains(String descreption);

    List<Villa> findByCostBetween(double cost1,double cost2);

    List<Villa> findByDescriptionLike(String descreption);


    @Query(value="SELECT * FROM villas WHERE name =:name",nativeQuery=true)
    List<Villa>  findVillaByName(@Param("name")String name);

    @Query(value="SELECT * FROM villas v WHERE v.cost <=:cost  ",nativeQuery=true)
    List<Villa>  filterVillasByCost(@Param("cost") double cost);

     List<VillaProjection> findAllProjectedBy();

}