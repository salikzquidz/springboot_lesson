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

import java.math.BigDecimal;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import static org.mockito.BDDMockito.given;
import static org.mockito.ArgumentMatchers.any;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;
import org.hamcrest.Matchers;

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

        given(beerService.getBeerById(testBeer.getId())).willReturn(testBeer); // mock the return value

        mockMvc.perform(get("/api/v1/beer/" + testBeer.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(testBeer.getId().toString())))
                .andExpect(jsonPath("$.name", is(testBeer.getName())))
                .andExpect(jsonPath("$.style", is(testBeer.getStyle().toString())))
                .andExpect(jsonPath("$.upc", is(testBeer.getUpc())))
                .andExpect(jsonPath("$.price", is(testBeer.getPrice().doubleValue()))); // https://stackoverflow.com/questions/71253810/json-path-with-big-decimal-expected-is-1000-but-was-1000
    }
}