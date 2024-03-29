package com.example.restmvc.controllers;

import com.example.restmvc.model.Beer;
import com.example.restmvc.services.BeerService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
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

    @PostMapping("/api/v1/beer")
    public ResponseEntity postBeer(@RequestBody Beer beer){
        Beer savedBeer = beerService.createBeer(beer);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/beer/" + savedBeer.getId().toString());

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @PutMapping("/api/v1/beer/{id}")
    public ResponseEntity updateBeer(@PathVariable("id") UUID id, @RequestBody Beer beer){
        beerService.updateBeer(id, beer);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/api/v1/beer/{id}")
    public ResponseEntity deleteBeer(@PathVariable("id") UUID id){
        beerService.deleteBeer(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
