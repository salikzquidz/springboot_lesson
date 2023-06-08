package com.example.restmvc.services;

import com.example.restmvc.model.Beer;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public interface BeerService {

    List<Beer> listBeers();
    Beer getBeerById(UUID id);
}
