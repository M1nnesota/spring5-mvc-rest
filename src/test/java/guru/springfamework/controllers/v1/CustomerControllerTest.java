package guru.springfamework.controllers.v1;

import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.services.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CustomerControllerTest {

    public static final String FIRST_NAME = "John";
    public static final String LAST_NAME = "Cena";
    public static final String CUSTOMER_URL = "www.cena.com";
    @Mock
    CustomerService customerService;

    @InjectMocks
    CustomerController customerController;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    public void testListCustomers() throws Exception {
        CustomerDTO john = new CustomerDTO();
        john.setId(1L);
        john.setFirstName(FIRST_NAME);
        john.setLastName(LAST_NAME);
        john.setCustomerUrl(CUSTOMER_URL);

        CustomerDTO bray = new CustomerDTO();
        bray.setFirstName("Bray");
        bray.setLastName("Wyatt");
        bray.setCustomerUrl("www.wyatt.com");

        List<CustomerDTO> customers = Arrays.asList(john, bray);

        when(customerService.getAllCustomers()).thenReturn(customers);

        mockMvc.perform(get("/api/v1/customers")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customers", hasSize(customers.size())));
    }

    @Test
    public void testGetCustomer() throws Exception {
        CustomerDTO john = new CustomerDTO();
        john.setId(1L);
        john.setFirstName(FIRST_NAME);
        john.setLastName(LAST_NAME);
        john.setCustomerUrl(CUSTOMER_URL);

        when(customerService.findCustomerById(anyLong())).thenReturn(john);

        mockMvc.perform(get("/api/v1/customers/123")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo(FIRST_NAME)))
                .andExpect(jsonPath("$.lastName", equalTo(LAST_NAME)))
                .andExpect(jsonPath("$.customerUrl", equalTo(CUSTOMER_URL)));
    }
}
