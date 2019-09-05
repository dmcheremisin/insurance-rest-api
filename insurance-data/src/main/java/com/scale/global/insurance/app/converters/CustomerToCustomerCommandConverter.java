package com.scale.global.insurance.app.converters;

import com.scale.global.insurance.app.engine.PriceCalculator;
import com.scale.global.insurance.app.entity.Customer;
import com.scale.global.insurance.app.model.CustomerDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CustomerToCustomerCommandConverter implements Converter<Customer, CustomerDTO> {

    private PriceCalculator priceCalculator;

    public CustomerToCustomerCommandConverter(PriceCalculator priceCalculator) {
        this.priceCalculator = priceCalculator;
    }

    @Override
    public CustomerDTO convert(Customer customer) {
        if(customer == null) {
            return null;
        }
        CustomerDTO customerDTO = CustomerDTO.builder()
                .insuranceNumber(customer.getInsuranceNumber())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .dateOfBirth(customer.getDateOfBirth())
                .inceptionOfThePolicy(customer.getInceptionOfThePolicy()).build();
        BigDecimal rate = priceCalculator.calculateRate(customer.getDateOfBirth(), customer.getInceptionOfThePolicy());
        customerDTO.setRate(rate);

        return customerDTO;
    }
}
