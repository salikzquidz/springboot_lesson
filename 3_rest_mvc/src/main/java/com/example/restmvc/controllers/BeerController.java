package com.example.restmvc.controllers;

import com.example.restmvc.model.Beer;
import com.example.restmvc.services.BeerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@Slf4j
public class BeerController {
    private final BeerService beerService;

    @RequestMapping("/api/v1/beers")
    public List<Beer> listBeers(){
        return beerService.listBeers();
    }

    @RequestMapping("/api/v1/beer/{beerId}")
    public Beer getBeerById(@PathVariable("beerId") UUID id){
        log.debug("Calling beer service");
        return beerService.getBeerById(id);
    }
}
