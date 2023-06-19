package com.example.restmvc.controllers;

import com.example.restmvc.model.Beer;
import com.example.restmvc.services.BeerService;
import com.example.restmvc.services.BeerServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.mockito.BDDMockito.given;
import static org.mockito.ArgumentMatchers.any;

@WebMvcTest(BeerController.class) // test splice
class BeerControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    BeerService beerService;

    BeerServiceImpl beerServiceImpl = new BeerServiceImpl();

    @Test
    void getBeerById() throws Exception {
        Beer testBeer = beerServiceImpl.listBeers().get(0); // get first beer in the mapping

        given(beerService.getBeerById(any(UUID.class))).willReturn(testBeer); // mock the return value

        mockMvc.perform(get("/api/v1/beer/" + UUID.randomUUID())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}