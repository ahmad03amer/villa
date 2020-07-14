package com.exalt.villaRentalSystem.controller;
import com.exalt.villaRentalSystem.dto.VillaDto;
import com.exalt.villaRentalSystem.model.Villa;
import com.exalt.villaRentalSystem.projection.VillaProjection;
import com.exalt.villaRentalSystem.service.VillaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log
@RestController
@RequiredArgsConstructor
public class VillaController {
    Logger log = LoggerFactory.getLogger(VillaController.class);

    @Autowired
    private VillaService villaService;

    @GetMapping("/api/v1/villas")
    public List<VillaDto> findAll(){
        List<VillaDto> villas = villaService.findAll();
        return villas;
    }

    @GetMapping("/api/v1/villa/{id}")
    public VillaDto findById(@PathVariable int id){
        VillaDto villa = villaService.findById(id);
        return villa;
    }

    @PostMapping("/api/v1/villa/add")
    public void save(@RequestBody VillaDto villaDto){
        log.info("user entered the saveVilla");
        villaService.save(villaDto);
    }

    @PutMapping("/api/v1/villa/update/{id}")
    public void update(@PathVariable int id, @ModelAttribute VillaDto villaDto) {
        log.info("user entered to updateVilla");
        villaService.update(id,villaDto);
    }

    @DeleteMapping("/api/v1/villas/{id}")
    public void delete( @PathVariable int id){
        log.info("User  entered /villas/{id} to deleteVilla ");
        villaService.delete(id);
    }

    @GetMapping("/api/v1/villas/search")
    @ResponseBody
    public List<Villa> findVillaByName(@RequestParam String name){
        List<Villa> villas= villaService.findVillaByName(name);
        return villas;
    }

    @GetMapping("/api/v1/villas/filter")
    @ResponseBody
    public List<Villa> filterVillasByCost(@RequestParam double cost){
        List<Villa> villas= villaService.filterVillasByCost(cost);
        return villas;
    }

    @GetMapping("/api/v1/villas/projectedVillas")
    public   List<VillaProjection> findAllProjectedBy(){
        log.info("User  entered /villas to getAllVillas ");
        return villaService.findAllProjectedBy();
    }

    @GetMapping("/api/v1/villas/pagingVilla")
    @ResponseBody
    public  Page<Villa> loadVillasPaging( @RequestParam(name = "page" , defaultValue = "0", required = false) int page
            , @RequestParam(name = "size", defaultValue = "2", required = false) int size){
        log.info("User  entered /villas to getAllVillas ");
        return villaService.loadVillasPaging(page,size);
    }

}
