package com.scale.global.insurance.app.converters;

import com.scale.global.insurance.app.entity.Customer;
import com.scale.global.insurance.app.model.CustomerDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CustomerCommandToCustomerConverter implements Converter<CustomerDTO, Customer> {

    @Override
    public Customer convert(CustomerDTO customerDTO) {
        if(customerDTO == null) {
            return null;
        }
        Customer customer = Customer.builder()
                .insuranceNumber(customerDTO.getInsuranceNumber())
                .firstName(customerDTO.getFirstName())
                .lastName(customerDTO.getLastName())
                .dateOfBirth(customerDTO.getDateOfBirth())
                .inceptionOfThePolicy(customerDTO.getInceptionOfThePolicy()).build();

        return customer;
    }
}
