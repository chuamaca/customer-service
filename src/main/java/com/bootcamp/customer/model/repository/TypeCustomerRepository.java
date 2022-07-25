package com.bootcamp.customer.model.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.bootcamp.customer.model.document.TypeCustomer;

public interface TypeCustomerRepository extends ReactiveMongoRepository<TypeCustomer, String> {

}
