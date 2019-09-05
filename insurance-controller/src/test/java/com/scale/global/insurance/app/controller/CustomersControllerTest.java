package com.scale.global.insurance.app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scale.global.insurance.app.model.CustomerDTO;
import com.scale.global.insurance.app.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CustomersControllerTest {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    @Mock
    CustomerService customerService;

    CustomersController customersController;

    MockMvc mockMvc;

    CustomerDTO customerDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        customersController = new CustomersController(customerService);
        mockMvc = MockMvcBuilders.standaloneSetup(customersController).build();

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
    void getCustomers() throws Exception {
        when(customerService.findAll()).thenReturn(Arrays.asList(new CustomerDTO(), new CustomerDTO()));

        mockMvc.perform(get("/api/v1/customers"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$", hasSize(2)));

        verify(customerService, times(1)).findAll();
    }

    @Test
    void getCustomer() throws Exception {
        when(customerService.findById(anyInt())).thenReturn(customerDTO);

        mockMvc.perform(get("/api/v1/customers/12"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.insuranceNumber", equalTo(customerDTO.getInsuranceNumber())))
                .andExpect(jsonPath("$.firstName", equalTo(customerDTO.getFirstName())))
                .andExpect(jsonPath("$.lastName", equalTo(customerDTO.getLastName())))
                .andExpect(jsonPath("$.dateOfBirth", equalTo(customerDTO.getDateOfBirth().format(DATE_FORMATTER))))
                .andExpect(jsonPath("$.inceptionOfThePolicy", equalTo(customerDTO.getInceptionOfThePolicy().format(DATE_FORMATTER))))
                .andExpect(jsonPath("$.rate").value(is(customerDTO.getRate()), BigDecimal.class));

        verify(customerService, times(1)).findById(anyInt());
    }

    @Test
    void saveCustomer() throws Exception {
        when(customerService.save(any())).thenReturn(customerDTO);

        mockMvc.perform(post("/api/v1/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(customerDTO)))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.insuranceNumber", equalTo(customerDTO.getInsuranceNumber())))
                .andExpect(jsonPath("$.firstName", equalTo(customerDTO.getFirstName())))
                .andExpect(jsonPath("$.lastName", equalTo(customerDTO.getLastName())))
                .andExpect(jsonPath("$.dateOfBirth", equalTo(customerDTO.getDateOfBirth().format(DATE_FORMATTER))))
                .andExpect(jsonPath("$.inceptionOfThePolicy", equalTo(customerDTO.getInceptionOfThePolicy().format(DATE_FORMATTER))))
                .andExpect(jsonPath("$.rate").value(is(customerDTO.getRate()), BigDecimal.class));

        verify(customerService, times(1)).save(any());
    }

    @Test
    void updateCustomer() throws Exception {
        when(customerService.save(any())).thenReturn(customerDTO);

        mockMvc.perform(put("/api/v1/customers/123")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(new ObjectMapper().writeValueAsString(customerDTO)))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.insuranceNumber", equalTo(customerDTO.getInsuranceNumber())))
                .andExpect(jsonPath("$.firstName", equalTo(customerDTO.getFirstName())))
                .andExpect(jsonPath("$.lastName", equalTo(customerDTO.getLastName())))
                .andExpect(jsonPath("$.dateOfBirth", equalTo(customerDTO.getDateOfBirth().format(DATE_FORMATTER))))
                .andExpect(jsonPath("$.inceptionOfThePolicy", equalTo(customerDTO.getInceptionOfThePolicy().format(DATE_FORMATTER))))
                .andExpect(jsonPath("$.rate").value(is(customerDTO.getRate()), BigDecimal.class));

        verify(customerService, times(1)).save(any());
    }

    @Test
    void deleteCustomer() throws Exception {
        mockMvc.perform(delete("/api/v1/customers/123"))
                .andExpect(status().isNoContent());

        verify(customerService, times(1)).deleteById(anyInt());
    }
}