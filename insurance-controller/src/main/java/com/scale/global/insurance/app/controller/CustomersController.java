package com.scale.global.insurance.app.controller;

import com.scale.global.insurance.app.model.CustomerDTO;
import com.scale.global.insurance.app.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@Validated
public class CustomersController {

    private CustomerService customerService;

    public CustomersController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<CustomerDTO> getCustomers() {
        return customerService.findAll();
    }

    @GetMapping("/{id}")
    public CustomerDTO getCustomer(@PathVariable @Min(1) Integer id) {
        return customerService.findById(id);
    }

    @PostMapping
    public CustomerDTO saveCustomer(@RequestBody @Valid CustomerDTO customerDTO) {
        customerDTO.setInsuranceNumber(null);
        return customerService.save(customerDTO);
    }

    @PutMapping("/{id}")
    public CustomerDTO updateCustomer(@PathVariable @Min(1) Integer id, @RequestBody @Valid CustomerDTO customerDTO) {
        customerDTO.setInsuranceNumber(id);
        return customerService.save(customerDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable Integer id) {
        customerService.deleteById(id);
    }
}
