package springframework.app.spring6mvc.controllers;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import springframework.app.spring6mvc.model.Beer;
import springframework.app.spring6mvc.services.BeerService;

@Slf4j
// @Controller
@RestController
// @AllArgsConstructor
@RequiredArgsConstructor
public class BeerController {
    
    private final BeerService beerService;

    public static final String BEER_PATH = "/api/v1/beer";
    public static final String BEER_PATH_ID = BEER_PATH + "/{beerId}";

    @PatchMapping(BEER_PATH_ID)
    public ResponseEntity<HttpStatus> patchBeerById(@PathVariable("beerId") UUID id, @RequestBody Beer beer){
        beerService.patchBeerById(id, beer);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(BEER_PATH_ID)
    public ResponseEntity<HttpStatus> deleteById(@PathVariable("beerId") UUID id){
        beerService.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping(BEER_PATH)
    public ResponseEntity<Beer> createBeer(@RequestBody Beer beer){

        Beer savedBeer = beerService.createBeer(beer);
        String location = ServletUriComponentsBuilder.fromCurrentRequest().toUriString() + "/" + savedBeer.getId().toString();
        URI uriLocation = URI.create(location);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriLocation);

        return new ResponseEntity<Beer>(savedBeer, headers, HttpStatus.CREATED);
    }

    @GetMapping(BEER_PATH)
    public List<Beer> listBeers(){
        return beerService.listBeers();
    }

    @GetMapping(BEER_PATH_ID)
    public Beer getBeer(@PathVariable("beerId") UUID beerId){
        log.debug("Get beer id in controller");
        return beerService.getBeerById(beerId);
    }

    @PutMapping(BEER_PATH_ID)
    public ResponseEntity<Beer> updateBeerById(@PathVariable("beerId") UUID id, @RequestBody Beer beer){
        Beer updatedBeer = beerService.updateBeerById(id, beer);

        String location = ServletUriComponentsBuilder.fromCurrentRequest().toUriString() + "/" + id;
        URI uriLocation = URI.create(location);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriLocation);
        return new ResponseEntity<>(updatedBeer, headers, HttpStatus.NO_CONTENT);

        // return new ResponseEntity<>(updatedBeer, headers, HttpStatus.NO_CONTENT);
    }
}
