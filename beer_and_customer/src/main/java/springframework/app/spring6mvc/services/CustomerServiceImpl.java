package springframework.app.spring6mvc.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;

import springframework.app.spring6mvc.model.Customer;

@Service
public class CustomerServiceImpl implements CustomerService{

    private Map<UUID, Customer> mapCustomer;
    public CustomerServiceImpl() {
        this.mapCustomer = new HashMap<>();

        Customer customer1 = Customer.builder()
        .id(UUID.randomUUID())
        .name("bob")
        .age(43)
        .build();

        Customer customer2 = Customer.builder()
        .id(UUID.randomUUID())
        .name("bazted")
        .age(25)
        .build();

        Customer customer3 = Customer.builder()
        .id(UUID.randomUUID())
        .name("alice")
        .age(12)
        .build();
        
        mapCustomer.put(customer1.getId(), customer1);
        mapCustomer.put(customer2.getId(), customer2);
        mapCustomer.put(customer3.getId(), customer3);
    }

    @Override
    public List<Customer> listCustomer() {
        return new ArrayList<>(mapCustomer.values());
    }

    @Override
    public Customer getCustomerById(UUID id) {
        return mapCustomer.get(id);
    }

    @Override
    public Customer createCustomer(Customer customer) {
        Customer newCustomer = Customer.builder()
            .id(UUID.randomUUID())
            .name(customer.getName())
            .age(customer.getAge())
            .build();

        mapCustomer.put(newCustomer.getId(), newCustomer);

        return newCustomer;
    }

    @Override
    public Customer updateCustomer(UUID id, Customer customer) {
        Customer existingCustomer = mapCustomer.get(id);
        existingCustomer.setName(customer.getName());
        existingCustomer.setAge(customer.getAge());

        System.out.println("halo");

        mapCustomer.put(id, existingCustomer);

        return existingCustomer;
    }

    @Override
    public void deleteCustomer(UUID id) {
        mapCustomer.remove(id);
    }

    @Override
    public void patchCustomerById(UUID id, Customer customer) {
        Customer existingCustomer = mapCustomer.get(id);

        if(customer.getAge() != null){
            existingCustomer.setAge(customer.getAge());
        }
        if(customer.getName() != null){
            existingCustomer.setName(customer.getName());
        }
    }
}
