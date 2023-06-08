package com.example.restmvc.services;

import com.example.restmvc.model.Beer;

import java.util.UUID;

public interface BeerService {
    Beer getBeerById(UUID id);
}
