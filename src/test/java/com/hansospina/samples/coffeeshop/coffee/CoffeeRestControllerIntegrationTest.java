package com.hansospina.samples.coffeeshop.coffee;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author <a href="mailto:hansospina@gmail.com">Hans Ospina</a>
 */
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

    @Test
    public void givenCoffee_updateCoffee_thenReturnUpdatedCoffee()
            throws Exception {

        String id = UUID.randomUUID().toString();

        Coffee tinto = new Coffee(id, CoffeeType.SOFT, CoffeeOrigin.ANTIOQUIA, "Tinto");

        List<Coffee> allCoffees = Collections.singletonList(tinto);

        given(service.list()).willReturn(allCoffees);

        // let's make it a stronger coffee
        Coffee strongTinto = new Coffee(tinto.getId(), CoffeeType.STRONG, tinto.getOrigin(), tinto.getName());

        given(service.update(any(String.class), any(Coffee.class))).willReturn(strongTinto);


        ObjectMapper mapper = new ObjectMapper();

        String json = mapper.writeValueAsString(strongTinto);

        mvc.perform(put("/api/coffee/v1/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.type", is(strongTinto.getType().name())));

        // key here is to verify that the controller responds properly to the PUT request and that it does
        // call the update method from the CoffeeService with a valid String as Id and a Coffee object as parameters.
        verify(service).update(any(String.class), any(Coffee.class));


    }


}
