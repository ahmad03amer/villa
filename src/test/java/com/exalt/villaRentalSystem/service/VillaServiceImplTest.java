package com.exalt.villaRentalSystem.service;


import com.exalt.villaRentalSystem.model.Villa;
import com.exalt.villaRentalSystem.VillaRentalSystemApplication;
import com.exalt.villaRentalSystem.repository.VillaRepository;
import com.exalt.villaRentalSystem.repository.internalRepository.VillaInRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes= VillaRentalSystemApplication.class)

public class VillaServiceImplTest {

    @Autowired
    private VillaRepository villaRepository;
    @Autowired
    private VillaInRepository villaInRepository;

    @Test
    public void contextLoads(){
    }


    /**
     * some tests applied on object with specific id , the id may not available
     */
    @Test
    public void getVillatest(){
        Optional<Villa> villa = villaRepository.findById(3);
         assertNotNull(villa);
    }

    @Test
    public void updateVillaTest(){
        Villa villa = villaRepository.findById(4).get();
        villa.setCost(7500);
        villaRepository.save(villa);
    }

    @Test
    public void deleteVillaTest() {
        if (villaRepository.existsById(96)) {
            villaRepository.deleteById(96);
        }
    }
        @Test
        public void countVillaTest(){
            System.out.println("\n\nTotal Records ----- >> "+villaRepository.count());

            }

    @Test
    public void readTest(){
       Villa villa = villaRepository.findById(2).get();
        assertNotNull(villa);
        assertEquals("sun",villa.getName());
        System.out.println("------------------------------>>"+villa.getDescription());
    }


    @Test
    public void updateTest(){
        Villa villa = villaRepository.findById(4).get();
        villa.setCost(2599);
        villaRepository.save(villa);
        assertEquals(2599,villa.getCost());
    }

     @Test
    public void findVillaByName(){
        List<Villa> villas = villaRepository.findByName("sun");
        villas.forEach(v ->System.out.println("------------------------"+ v.getName()) );
    }

    @Test
    public void findVillaByNameAndCost(){
        List<Villa> villas = villaRepository.findByNameAndCost("MOOOON",7500);
        villas.forEach(v ->System.out.println("------------------------"+ v.getAddress()) );

    }

    @Test
    public void findVillaByCostGreaterThan(){
        List<Villa> villas = villaRepository.findByCostGreaterThan(1000);
        villas.forEach(v ->System.out.println("------------------------"+ v.getName()) );
    }

    @Test
    public void findVillaByDescriptionContains(){
        List<Villa> villas = villaRepository.findByDescriptionContains("test");
        villas.forEach(v ->System.out.println("------------------------"+ v.getName()) );

    }

    @Test
    public void findVillaByCostBetween(){
        List<Villa> villas = villaRepository.findByCostBetween(1000,2000);
        villas.forEach(v ->System.out.println("------------------------"+ v.getName()) );
    }

    @Test
    public void findVillaByDescriptionLike(){
        List<Villa> villas = villaRepository.findByDescriptionContains("nice");
        villas.forEach(v ->System.out.println("------------------------"+ v.getName()) );
    }


    @Test
    public void testFindVillaByIdIn(){
        Pageable pagable =  PageRequest.of(0,1);
        Page<Villa> villas = villaRepository.findByIdIn(Arrays.asList(0,1,2),pagable);
        villas.forEach(v -> System.out.println(v.getName()));
    }


    @Test
    public void testFindAllPaging(){
        PageRequest pageable = PageRequest.of(0, 2);
         Page<Villa> villas = villaRepository.findAll(pageable);
         villas.forEach(v->System.out.println(v.getName()));
    }

   @Test
    public void testFindAllSort(){
        villaRepository.findAll(Sort.by(Sort.Direction.DESC,"name","cost")).forEach(p->System.out.println(p.getName()));
    }

    @Test
    public void testFindAllPaginAndSorting(){
        Pageable pagable =  PageRequest.of(0,1, Sort.Direction.DESC ,"name");
        villaRepository.findAll(pagable).forEach(v->System.out.println(v.getName()));
    }

    @Test
    public List<Villa> getvillasTest(){
        System.out.println("hi");
        return villaInRepository.findAll();
    }

}
