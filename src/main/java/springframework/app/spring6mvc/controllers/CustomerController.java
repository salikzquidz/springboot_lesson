package springframework.app.spring6mvc.controllers;

import java.net.URI;
import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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
import springframework.app.spring6mvc.model.Customer;
import springframework.app.spring6mvc.services.CustomerService;

@RestController
@AllArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    // public CustomerController(CustomerService customerService) {
    //     this.customerService = customerService;
    // }

    @PatchMapping("/api/v1/customers/{customerId}")
    public ResponseEntity patchCustomerById(@PathVariable("customerId") UUID id, @RequestBody Customer customer){
        customerService.patchCustomerById(id, customer);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/api/v1/customers/{customerId}")
    public ResponseEntity deleteCustomerById(@PathVariable("customerId") UUID id){
        customerService.deleteCustomer(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/api/v1/customers/{customerId}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable("customerId") UUID id, @RequestBody Customer customer){
        Customer updatedCustomer = customerService.updateCustomer(id, customer);

        String location = ServletUriComponentsBuilder.fromCurrentRequest().toString() + "/" + id.toString();
        URI uriLocation = URI.create(location);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriLocation);

        return new ResponseEntity<>(updatedCustomer, headers, HttpStatus.NO_CONTENT);
    }
    
    @RequestMapping(value =  "/api/v1/customers", method = RequestMethod.GET)
    public List<Customer> listCustomer(){
        return customerService.listCustomer();
    }

    @RequestMapping(value =  "/api/v1/customers/{customerId}", method = RequestMethod.GET)
    public Customer getCustomerById(@PathVariable("customerId") UUID id){
        return customerService.getCustomerById(id);
    }

    @PostMapping(value = "/api/v1/customers")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer){
        Customer savedCustomer = customerService.createCustomer(customer);

        String location = ServletUriComponentsBuilder.fromCurrentRequest().toUriString() + "/" + savedCustomer.getId();
        URI uriLocation = URI.create(location);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriLocation);

        return ResponseEntity.created(headers.getLocation()).body(savedCustomer);
    }


}
