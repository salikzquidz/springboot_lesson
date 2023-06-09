package com.example.restmvc.services;

import com.example.restmvc.model.Customer;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CustomerServiceImpl implements CustomerService{

    private Map<UUID, Customer> customerMap;
    public CustomerServiceImpl() {
        this.customerMap = new HashMap<>();

        Customer cust1 = Customer.builder().id(UUID.randomUUID()).name("Ahmad").age(25).build();
        Customer cust2 = Customer.builder().id(UUID.randomUUID()).name("Byzantine").age(26).build();
        Customer cust3 = Customer.builder().id(UUID.randomUUID()).name("Cristiano").age(27).build();

        customerMap.put(cust1.getId(), cust1);
        customerMap.put(cust2.getId(), cust2);
        customerMap.put(cust3.getId(), cust3);
    }

    @Override
    public List<Customer> listCustomers(){
        return new ArrayList<>(customerMap.values());
    }

    @Override
    public Customer getCustomerById(UUID id){
        return customerMap.get(id);
    }
}
