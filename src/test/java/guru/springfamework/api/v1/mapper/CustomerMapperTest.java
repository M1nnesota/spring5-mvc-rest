package guru.springfamework.api.v1.mapper;

import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.domain.Customer;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CustomerMapperTest {

    private static final Long ID = 100500L;
    private static final String FIRST_NAME = "Name";
    private static final String LAST_NAME = "Surname";
    private static final String URL = "Url";

    CustomerMapper mapper = CustomerMapper.INSTANCE;

    @Test
    public void customerToCustomerDTO() {

        Customer customer = new Customer();
        customer.setId(ID);
        customer.setFirstName(FIRST_NAME);
        customer.setLastName(LAST_NAME);
        customer.setCustomerUrl(URL);

        CustomerDTO customerDTO = mapper.customerToCustomerDTO(customer);

        assertEquals(Long.valueOf(ID), customerDTO.getId());
        assertEquals(FIRST_NAME, customerDTO.getFirstName());
        assertEquals(LAST_NAME, customerDTO.getLastName());
        assertEquals(URL, customerDTO.getCustomerUrl());
    }

}
