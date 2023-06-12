package com.example.restmvc.services;

import com.example.restmvc.model.Customer;

import java.util.List;
import java.util.UUID;

public interface CustomerService {
    List<Customer> listCustomers();
    Customer getCustomerById(UUID uuid);
    Customer createCustomer(Customer customer);
}
