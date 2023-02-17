package springframework.app.spring6mvc.services;

import java.util.List;
import java.util.UUID;

import springframework.app.spring6mvc.model.Customer;

public interface CustomerService {
    
    List<Customer> listCustomer();
    Customer getCustomerById(UUID id);
    Customer createCustomer(Customer customer);
    Customer updateCustomer(UUID id, Customer customer);
    void deleteCustomer(UUID id);
    void patchCustomerById(UUID id, Customer customer);
}
