package com.scale.global.insurance.app.repositories;

import com.scale.global.insurance.app.entity.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {
}
