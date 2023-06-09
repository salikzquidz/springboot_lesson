package com.example.restmvc.controllers;

import com.example.restmvc.model.Customer;
import com.example.restmvc.services.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
