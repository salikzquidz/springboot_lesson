package com.example.restmvc.services;

import com.example.restmvc.model.Beer;
import com.example.restmvc.model.BeerStyle;

import java.math.BigDecimal;
import java.util.UUID;

public class BeerServiceImpl implements BeerService{

    @Override
    public Beer getBeerById(UUID id) {
        return Beer.builder()
                .id(UUID.randomUUID())
                .name("Haram")
                .price(new BigDecimal("59.99"))
                .upc("100")
                .style(BeerStyle.LAGER)
                .build();
    }
}
