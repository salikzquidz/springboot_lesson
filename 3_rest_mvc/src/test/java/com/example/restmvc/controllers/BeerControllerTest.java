package com.example.restmvc.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BeerControllerTest {

    @Autowired
    BeerController beerController;

    @BeforeEach
    void setUp() {

    }

    @Test
    void listBeers(){
        System.out.println(beerController.listBeers());
    }

    @Test
    void getBeerById() {
        System.out.println(beerController.getBeerById(UUID.randomUUID()));
    }
}