package com.example.restmvc.controllers;

import com.example.restmvc.model.Customer;
import com.example.restmvc.services.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
    @RequestMapping("/api/v1/customers")
    public List<Customer> listCustomers(){
        return customerService.listCustomers();
    }

    @RequestMapping("/api/v1/customer/{id}")
    public Customer getCustomerById(@PathVariable("id") UUID id){
        return customerService.getCustomerById(id);
    }

    @PostMapping("/api/v1/customer")
    public ResponseEntity createCustomer(@RequestBody Customer customer){
        Customer savedCustomer = customerService.createCustomer(customer);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Location", "/api/v1/customer/" + savedCustomer.getId().toString());

        return new ResponseEntity(httpHeaders, HttpStatus.CREATED);
    }

    @PutMapping("/api/v1/customer/{id}")
    public ResponseEntity updateCustomer(@PathVariable("id") UUID id, @RequestBody Customer customer){
        customerService.updateCustomer(id, customer);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/api/v1/customer/{id}")
    public ResponseEntity deleteCustomer(@PathVariable("id") UUID id){
        customerService.deleteCustomer(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
