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

    @Override
    public Customer createCustomer(Customer customer){
        Customer savedCustomer = Customer.builder()
                .id(UUID.randomUUID())
                .name(customer.getName())
                .age(customer.getAge())
                .build();
        customerMap.put(savedCustomer.getId(), savedCustomer);

        return savedCustomer;
    }

    @Override
    public void updateCustomer(UUID id, Customer customer){
        Customer selectedCustomer = customerMap.get(id);
        selectedCustomer.setName(customer.getName());
        selectedCustomer.setAge(customer.getAge());

        customerMap.put(selectedCustomer.getId(), selectedCustomer);
    }

    @Override
    public void deleteCustomer(UUID id){
        customerMap.remove(id);
    }
}
