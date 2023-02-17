package springframework.app.spring6mvc.services;

import java.util.List;
import java.util.UUID;

import springframework.app.spring6mvc.model.Beer;

public interface BeerService {
    List<Beer> listBeers();
    Beer getBeerById(UUID id);
    Beer createBeer(Beer beer);
    Beer updateBeerById(UUID id, Beer beer);
    void deleteById(UUID id);
    void patchBeerById(UUID id, Beer beer);
}
