package com.scale.global.insurance.app.service.impl;

import com.scale.global.insurance.app.converters.CustomerCommandToCustomerConverter;
import com.scale.global.insurance.app.converters.CustomerToCustomerCommandConverter;
import com.scale.global.insurance.app.entity.Customer;
import com.scale.global.insurance.app.exceptions.CustomerNotFoundException;
import com.scale.global.insurance.app.model.CustomerDTO;
import com.scale.global.insurance.app.repositories.CustomerRepository;
import com.scale.global.insurance.app.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CustomerServiceImplTest {

    @Mock
    CustomerRepository customerRepository;
    @Mock
    CustomerCommandToCustomerConverter customerCommandToCustomerConverter;
    @Mock
    CustomerToCustomerCommandConverter customerToCustomerCommandConverter;

    CustomerService customerService;

    Customer customer;
    CustomerDTO customerDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        customerService = new CustomerServiceImpl(customerRepository, customerCommandToCustomerConverter,
                                                    customerToCustomerCommandConverter);
        customer = Customer.builder()
                .insuranceNumber(123)
                .firstName("Tirion")
                .lastName("Lannister")
                .dateOfBirth(LocalDate.now().minusYears(30))
                .inceptionOfThePolicy(LocalDate.now().minusYears(5).minusDays(5))
                .build();
        customerDTO = CustomerDTO.builder()
                .insuranceNumber(123)
                .firstName("Tirion")
                .lastName("Lannister")
                .dateOfBirth(LocalDate.now().minusYears(30))
                .inceptionOfThePolicy(LocalDate.now().minusYears(5).minusDays(5))
                .rate(new BigDecimal("10.23"))
                .build();
    }

    @Test
    void findById() {
        when(customerRepository.findById(anyInt())).thenReturn(Optional.of(customer));
        when(customerToCustomerCommandConverter.convert(any())).thenReturn(customerDTO);
        CustomerDTO customerById = customerService.findById(1);

        assertEquals(customerById.getInsuranceNumber(), customer.getInsuranceNumber());
        assertEquals(customerById.getFirstName(), customer.getFirstName());
        assertEquals(customerById.getLastName(), customer.getLastName());
        assertEquals(customerById.getDateOfBirth(), customer.getDateOfBirth());
        assertEquals(customerById.getInceptionOfThePolicy(), customer.getInceptionOfThePolicy());
    }

    @Test
    void findByIdException() {
        when(customerRepository.findById(anyInt())).thenReturn(Optional.empty());
        assertThrows(CustomerNotFoundException.class, () -> customerService.findById(31));
    }

    @Test
    void findAll() {
        when(customerRepository.findAll()).thenReturn(Arrays.asList(new Customer(), new Customer()));
        when((customerToCustomerCommandConverter.convert(any()))).thenReturn(new CustomerDTO());
        List<CustomerDTO> all = customerService.findAll();
        assertEquals(2, all.size());
        verify(customerRepository, times(1)).findAll();
        verify(customerToCustomerCommandConverter, times(2)).convert(any());
    }

    @Test
    void save() {
        when(customerRepository.save(any())).thenReturn(customer);
        when((customerToCustomerCommandConverter.convert(any()))).thenReturn(customerDTO);
        CustomerDTO savedCustomer = customerService.save(new CustomerDTO());
        assertNotNull(savedCustomer);
        assertEquals(savedCustomer.getInsuranceNumber(), customer.getInsuranceNumber());
        assertEquals(savedCustomer.getFirstName(), customer.getFirstName());
        assertEquals(savedCustomer.getLastName(), customer.getLastName());
        assertEquals(savedCustomer.getDateOfBirth(), customer.getDateOfBirth());
        assertEquals(savedCustomer.getInceptionOfThePolicy(), customer.getInceptionOfThePolicy());
    }

    @Test
    void deleteById() {
        customerService.deleteById(1);
        verify(customerRepository, times(1)).deleteById(anyInt());
    }
}