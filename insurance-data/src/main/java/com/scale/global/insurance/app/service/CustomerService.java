package com.scale.global.insurance.app.service;

import com.scale.global.insurance.app.model.CustomerDTO;

import java.util.List;

public interface CustomerService {

    CustomerDTO findById(Integer id);

    List<CustomerDTO> findAll();

    CustomerDTO save(CustomerDTO customer);

    void deleteById(Integer id);
}
