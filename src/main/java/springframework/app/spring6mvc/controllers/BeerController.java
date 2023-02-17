package springframework.app.spring6mvc.controllers;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @PatchMapping("/api/v1/beer/{beerId}")
    public ResponseEntity patchBeerById(@PathVariable("beerId") UUID id, @RequestBody Beer beer){
        beerService.patchBeerById(id, beer);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/api/v1/beer/{beerId}")
    public ResponseEntity deleteById(@PathVariable("beerId") UUID id){
        beerService.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/api/v1/beer")
    public ResponseEntity<Beer> createBeer(@RequestBody Beer beer){

        Beer savedBeer = beerService.createBeer(beer);
        String location2 = ServletUriComponentsBuilder.fromCurrentRequest().toUriString() + "/" + savedBeer.getId().toString();

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(location2));

        return ResponseEntity.created(headers.getLocation()).body(savedBeer);
    }

    @RequestMapping(value = "/api/v1/beer", method = RequestMethod.GET)
    public List<Beer> listBeers(){
        return beerService.listBeers();
    }

    @RequestMapping(value =  "/api/v1/beer/{beerId}", method = RequestMethod.GET)
    public Beer getBeer(@PathVariable("beerId") UUID beerId){
        log.debug("Get beer id in controller");
        return beerService.getBeerById(beerId);
    }

    @PutMapping("/api/v1/beer/{beerId}")
    public ResponseEntity<Beer> updateBeerById(@PathVariable("beerId") UUID id, @RequestBody Beer beer){
        Beer updatedBeer = beerService.updateBeerById(id, beer);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("http://localhost"));

        return new ResponseEntity<>(updatedBeer, headers, HttpStatus.NO_CONTENT);
    }
}
