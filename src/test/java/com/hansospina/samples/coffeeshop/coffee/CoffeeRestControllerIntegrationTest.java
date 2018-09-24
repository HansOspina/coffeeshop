package com.hansospina.samples.coffeeshop.coffee;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CoffeeController.class)
public class CoffeeRestControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CoffeeService service;


    @Test
    public void givenCoffees_whenGetCoffees_thenReturnJsonArray()
            throws Exception {

        String id = UUID.randomUUID().toString();

        Coffee tinto = new Coffee(id, CoffeeType.SOFT, CoffeeOrigin.ANTIOQUIA, "Tinto");

        List<Coffee> allCoffees = Collections.singletonList(tinto);

        given(service.list()).willReturn(allCoffees);

        mvc.perform(get("/api/coffee/v1/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is(tinto.getName())));
    }



}
