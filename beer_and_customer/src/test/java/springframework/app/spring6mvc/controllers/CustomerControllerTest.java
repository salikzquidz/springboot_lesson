package springframework.app.spring6mvc.controllers;

import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import springframework.app.spring6mvc.model.Customer;
import springframework.app.spring6mvc.services.CustomerService;
import springframework.app.spring6mvc.services.CustomerServiceImpl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CustomerService customerService;

    @Autowired
    ObjectMapper objectMapper;

    @Captor
    ArgumentCaptor<UUID> uuidArgumentCaptor;

    @Captor
    ArgumentCaptor<Customer> customerArgumentCaptor;

    CustomerServiceImpl customerServiceImpl = new CustomerServiceImpl();

    @Test
    void testGetCustomerById() throws Exception {

        Customer testCustomer = customerServiceImpl.listCustomer().get(0);
        given(customerService.getCustomerById(testCustomer.getId())).willReturn(testCustomer);

        mockMvc.perform(get("/api/v1/customers/" + testCustomer.getId()).accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.name", Is.is(testCustomer.getName())));
    }

    @Test
    void testListCustomer() throws Exception {
        List<Customer> listCustomers = customerServiceImpl.listCustomer();
        given(customerService.listCustomer()).willReturn(listCustomers);

        mockMvc.perform(get("/api/v1/customers"))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.length()", Is.is(3)));
    }

    @Test
    void testCreateCustomer() throws Exception {

        Customer customerFakeInput = customerServiceImpl.listCustomer().get(0);
        customerFakeInput.setId(null);

        given(customerService.createCustomer(any(Customer.class))).willReturn(customerServiceImpl.listCustomer().get(1));

        mockMvc.perform(post("/api/v1/customers")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(customerFakeInput)))
            .andExpect(header().exists("Location"))
            .andExpect(status().isCreated());
    }

    @Test
    void testUpdateCustomer() throws Exception {

        Customer customer = customerServiceImpl.listCustomer().get(0);

        mockMvc.perform(put("/api/v1/customers/" + customer.getId())
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(customer))
        ).andExpect(status().isNoContent());

        verify(customerService).updateCustomer(any(UUID.class), any(Customer.class));
    }

    @Test
    void testDeleteCustomer() throws Exception {

        Customer customer = customerServiceImpl.listCustomer().get(0);

        mockMvc.perform(delete("/api/v1/customers/" + customer.getId()))
        .andExpect(status().isNoContent());

        ArgumentCaptor<UUID> uuidArgumentCaptor = ArgumentCaptor.forClass(UUID.class);
        verify(customerService).deleteCustomer(uuidArgumentCaptor.capture());
        assertThat(customer.getId()).isEqualTo(uuidArgumentCaptor.getValue());

        // assertThat(customer.getId(), uuidArgumentCaptor.getValue());
    }

    @Test
    void testPatchCustomer() throws Exception {
        Customer customer = customerServiceImpl.listCustomer().get(0);

        Map<String, Object> customerMap = new HashMap<>();

        customerMap.put("name", "New root beer");

        mockMvc.perform(patch("/api/v1/customers/" + customer.getId())
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(customerMap))
        ).andExpect(status().isNoContent());

        verify(customerService).patchCustomerById(uuidArgumentCaptor.capture(), customerArgumentCaptor.capture());

        assertThat(customer.getId()).isEqualTo(uuidArgumentCaptor.getValue());
        assertThat(customerMap.get("name")).isEqualTo(customerArgumentCaptor.getValue().getName());
    }
}
