package springframework.app.spring6mvc.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import lombok.extern.slf4j.Slf4j;
import springframework.app.spring6mvc.model.Beer;
import springframework.app.spring6mvc.model.BeerStyle;

@Service
@Slf4j
public class BeerServiceImpl implements BeerService{

    private Map<UUID, Beer> beerMap;

    @Override
    public List<Beer> listBeers(){
        return new ArrayList<>(beerMap.values());
    }

    public BeerServiceImpl(){
        this.beerMap = new HashMap<>();

        Beer beer1 = Beer.builder()
            .id(UUID.randomUUID())
            .version(1)
            .beerName("Galaxy Cat")
            .beerStyle(BeerStyle.ALE)
            .upc("123")
            .price(new BigDecimal("13.99"))
            .quantityOnHand(123)
            .createdDate(LocalDateTime.now())
            .updateDate(LocalDateTime.now())
            .build();

            Beer beer2 = Beer.builder()
            .id(UUID.randomUUID())
            .version(2)
            .beerName("Saturn Ring")
            .beerStyle(BeerStyle.GOSE)
            .upc("124")
            .price(new BigDecimal("12.99"))
            .quantityOnHand(123)
            .createdDate(LocalDateTime.now())
            .updateDate(LocalDateTime.now())
            .build();

            Beer beer3 = Beer.builder()
            .id(UUID.randomUUID())
            .version(3)
            .beerName("Pluto Miniscule")
            .beerStyle(BeerStyle.IPA)
            .upc("125")
            .price(new BigDecimal("11.99"))
            .quantityOnHand(123)
            .createdDate(LocalDateTime.now())
            .updateDate(LocalDateTime.now())
            .build();

            beerMap.put(beer1.getId(), beer1);
            beerMap.put(beer2.getId(), beer2);
            beerMap.put(beer3.getId(), beer3);
    }
    
    @Override
    public Beer getBeerById(UUID id) {
        log.debug("Get Beer by Id service is called, the id is : "+ id.toString());
        return beerMap.get(id);
    }

    @Override
    public Beer createBeer(Beer beer) {
        Beer newBeer = Beer.builder()
            .id(UUID.randomUUID())
            .version(beer.getVersion())
            .beerName(beer.getBeerName())
            .beerStyle(beer.getBeerStyle())
            .upc(beer.getUpc())
            .price(beer.getPrice())
            .quantityOnHand(beer.getQuantityOnHand())
            .createdDate(LocalDateTime.now())
            .updateDate(LocalDateTime.now())
            .build();

        beerMap.put(beer.getId(), newBeer);
        return newBeer;
    }

    @Override
    public Beer updateBeerById(UUID id, Beer beer) {

        Beer existingBeer = beerMap.get(id);
        existingBeer.setBeerName(beer.getBeerName());
        existingBeer.setPrice(beer.getPrice());
        existingBeer.setUpc(beer.getUpc());

        beerMap.put(id, existingBeer);
        return existingBeer;
    }

    @Override
    public void deleteById(UUID id) {
        beerMap.remove(id);
    }

    @Override
    public void patchBeerById(UUID id, Beer beer) {
        Beer existingBeer = beerMap.get(id);
        
        if (StringUtils.hasText(beer.getBeerName())){
            existingBeer.setBeerName(beer.getBeerName());
        }

        if (beer.getBeerStyle() != null) {
            existingBeer.setBeerStyle(beer.getBeerStyle());
        }

        if (beer.getPrice() != null) {
            existingBeer.setPrice(beer.getPrice());
        }

        if (beer.getQuantityOnHand() != null){
            existingBeer.setQuantityOnHand(beer.getQuantityOnHand());
        }

        if (StringUtils.hasText(beer.getUpc())) {
            existingBeer.setUpc(beer.getUpc());
        }
    }
}
