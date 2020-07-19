package com.exalt.villaRentalSystem.service;


import com.exalt.villaRentalSystem.VillaRentalSystemApplication;
import com.exalt.villaRentalSystem.model.Villa;
import com.exalt.villaRentalSystem.repository.VillaRepository;
import javassist.NotFoundException;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes= VillaRentalSystemApplication.class)
public class VillaServiceImplTest {

    @Autowired
    private VillaRepository villaRepository;



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
        System.out.println("Total Records ----------> "+villaRepository.count());

    }

    @Test
    public void readTest(){
        Villa villa = villaRepository.findById(2).get();
        assertNotNull(villa);
        assertEquals("sun",villa.getName());
        System.out.println("---------->"+villa.getDescription());
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
        villas.forEach(v ->System.out.println("---------->"+ v.getName()) );
    }

    @Test
    public void findVillaByNameAndCost(){
        List<Villa> villas = villaRepository.findByNameAndCost("MOOOON",7500);
        villas.forEach(v ->System.out.println("---------->"+ v.getAddress()) );

    }

    @Test
    public void findVillaByCostGreaterThan(){
        List<Villa> villas = villaRepository.findByCostGreaterThan(1000);
        villas.forEach(v ->System.out.println("---------->"+ v.getName()) );
    }

    @Test
    public void findVillaByDescriptionContains(){
        List<Villa> villas = villaRepository.findByDescriptionContains("test");
        villas.forEach(v ->System.out.println("---------->"+ v.getName()) );

    }

    @Test
    public void findVillaByCostBetween(){
        List<Villa> villas = villaRepository.findByCostBetween(1000,2000);
        villas.forEach(v ->System.out.println("---------->"+ v.getName()) );
    }

    @Test
    public void findVillaByDescriptionLike(){
        List<Villa> villas = villaRepository.findByDescriptionContains("nice");
        villas.forEach(v ->System.out.println("---------->"+ v.getName()) );
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


    TestRestTemplate testRestTemplate = new TestRestTemplate();
    @BeforeEach
    public void createVilla(){
        Villa villa = new Villa();
        villa.setId(1);
        villa.setName("quds");
        villa.setAddress("jerusalem");
        villa.setCost(1650);
        villa.setStatus(false);
        villa.setDescription("center");
        villaRepository.save(villa);
    }

    @Test
    @DisplayName("add Villa ")
    public void addVillaTest(){
        Villa villa = new Villa();
        villa.setId(1);
        villa.setName("quds");
        villa.setAddress("jerusalem");
        villa.setCost(1650);
        villa.setStatus(false);
        villa.setDescription("center");
        villaRepository.save(villa);

        Villa testVilla = villaRepository.findById(1).get();
        System.out.println(testVilla.getName());
        Assert.assertNotNull(testVilla);
        Assert.assertEquals("quds",testVilla.getName());
    }

    @Test
    public void findAllTest(){
        List<Villa> villas = (List<Villa>) villaRepository.findAll();
        Assert.assertNotNull(villas);
    }

    @Test
    public void findByIdTest() throws NotFoundException {
        Villa villa =villaRepository.findById(1).orElse(null);
        Assert.assertTrue("Villa does not exist",villa != null);
        if (villa != null){
            assertAll("villa",
                    () -> assertEquals("quds",villa.getName()),
                    () -> assertEquals("center",villa.getDescription()),
                    () -> assertEquals("jerusalem",villa.getAddress()),
                    () -> assertEquals(1650,villa.getCost()),
                    () -> assertEquals(false,villa.isStatus())
            );
        }else {
            throw new NotFoundException("The villa with id=2 does not exist");
        }
    }

    @Test
    public void addVillaAsJsonInputTest() throws  JSONException, NotFoundException {
        String addVillaUrl = "http://localhost:8084/api/v1/villa";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        JSONObject villaJsonObject = new JSONObject();
        villaJsonObject.put("name","rose9");
        villaJsonObject.put("description","center");
        villaJsonObject.put("address","jerusalem");
        villaJsonObject.put("cost",1300);
        villaJsonObject.put("status",true);

        HttpEntity<String> request = new HttpEntity<String>(villaJsonObject.toString(), headers);
        Villa villa = restTemplate.postForObject(addVillaUrl, request, Villa.class);
        if (villa != null){
            assertAll("villa",
                    () -> assertEquals("rose9",villa.getName()),
                    () -> assertEquals("center",villa.getDescription()),
                    () -> assertEquals("jerusalem",villa.getAddress()),
                    () -> assertEquals(1300,villa.getCost()),
                    () -> assertEquals(true,villa.isStatus())
            );
        }else {
            throw new NotFoundException("The villa with id=2 does not exist");
        }
        assertNotNull(villa,"villa Object is null!!");
    }

   @Test
    public void getVillaTest()  {
        String getVillaUrl = "http://localhost:8084/api/v1/villas";
        RestTemplate restTemplate = new RestTemplate();
       String villas =  restTemplate.getForObject(getVillaUrl, String.class);

       Assert.assertNotNull(villas,"there is no villas to get");
   }

   @Test
    public void deleteEmployee()
    {
        final String deleteVillaUrl = "http://localhost:8084/api/v1/villas/{id}";

        Map<String, String> params = new HashMap<String, String>();
        params.put("id", "1");

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete( deleteVillaUrl, params);
    }
}







