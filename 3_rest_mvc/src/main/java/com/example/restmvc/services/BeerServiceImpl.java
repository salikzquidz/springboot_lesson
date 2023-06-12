package com.example.restmvc.services;

import com.example.restmvc.model.Beer;
import com.example.restmvc.model.BeerStyle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
@Slf4j
public class BeerServiceImpl implements BeerService{
    private Map<UUID, Beer> beerMap;

    public BeerServiceImpl(){
        this.beerMap = new HashMap<>();

        Beer beer1 = Beer.builder().id(UUID.randomUUID()).name("Garam").style(BeerStyle.ALE)
                .price(new BigDecimal("12.99")).upc("1").build();
        Beer beer2 = Beer.builder().id(UUID.randomUUID()).name("Alkohol").style(BeerStyle.IPA)
                .price(new BigDecimal("15.99")).upc("2").build();
        Beer beer3 = Beer.builder().id(UUID.randomUUID()).name("Methanol").style(BeerStyle.GOSE)
                .price(new BigDecimal("19.99")).upc("3").build();

        beerMap.put(beer1.getId(), beer1);
        beerMap.put(beer2.getId(), beer2);
        beerMap.put(beer3.getId(), beer3);
    }

    @Override
    public List<Beer> listBeers(){
        log.debug("List beers is called");
        return new ArrayList<>(beerMap.values());
    }

    @Override
    public Beer getBeerById(UUID id) {
        log.debug("Get Beer By ID was called");
        return beerMap.get(id);
    }

    @Override
    public Beer createBeer(Beer beer){
        Beer savedBeer = Beer.builder()
                .id(UUID.randomUUID())
                .name(beer.getName())
                .style(beer.getStyle())
                .upc(beer.getUpc())
                .price(beer.getPrice())
                .build();

        beerMap.put(savedBeer.getId(), savedBeer);
        return savedBeer;
    }
}
