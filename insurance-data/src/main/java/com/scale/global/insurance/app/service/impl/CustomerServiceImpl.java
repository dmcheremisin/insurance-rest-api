package com.scale.global.insurance.app.service.impl;

import com.scale.global.insurance.app.converters.CustomerCommandToCustomerConverter;
import com.scale.global.insurance.app.converters.CustomerToCustomerCommandConverter;
import com.scale.global.insurance.app.entity.Customer;
import com.scale.global.insurance.app.exceptions.CustomerNotFoundException;
import com.scale.global.insurance.app.model.CustomerDTO;
import com.scale.global.insurance.app.repositories.CustomerRepository;
import com.scale.global.insurance.app.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;
    private CustomerCommandToCustomerConverter customerCommandToCustomerConverter;
    private CustomerToCustomerCommandConverter customerToCustomerCommandConverter;

    public CustomerServiceImpl(CustomerRepository customerRepository,
                               CustomerCommandToCustomerConverter customerCommandToCustomerConverter,
                               CustomerToCustomerCommandConverter customerToCustomerCommandConverter) {
        this.customerRepository = customerRepository;
        this.customerCommandToCustomerConverter = customerCommandToCustomerConverter;
        this.customerToCustomerCommandConverter = customerToCustomerCommandConverter;
    }

    @Override
    public CustomerDTO findById(Integer id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException(id));
        return customerToCustomerCommandConverter.convert(customer);
    }

    @Override
    public List<CustomerDTO> findAll() {
        List<CustomerDTO> list = StreamSupport.stream(customerRepository.findAll().spliterator(), false)
                .map(c -> customerToCustomerCommandConverter.convert(c))
                .collect(Collectors.toList());
        return list;
    }

    @Override
    public CustomerDTO save(CustomerDTO customerDTO) {
        Customer customer = customerCommandToCustomerConverter.convert(customerDTO);
        Customer savedCustomer = customerRepository.save(customer);
        CustomerDTO convertedCommand = customerToCustomerCommandConverter.convert(savedCustomer);
        return convertedCommand;
    }

    @Override
    public void deleteById(Integer id) {
        customerRepository.deleteById(id);
    }
}
