package com.bootcamp.customer.model.service;

import com.bootcamp.customer.dto.CustomerRequestDto;
import com.bootcamp.customer.dto.CustomerResponseDto;
import com.bootcamp.customer.model.document.Customer;
import com.bootcamp.customer.model.document.TypeCustomer;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerService {

	Flux<Customer> getAll();

	Mono<Customer> save(Customer customer);

	Mono<Customer> findById(String documentNumber);

	Mono<Boolean> existById(String documentNumber);

	Mono<Void> delete(String documentNumber);

	Mono<Customer> update(String documentNumber, Customer customer);
	
	Flux<TypeCustomer> findAllTypeCustomer(TypeCustomer typeCustomer);
	
	Mono<TypeCustomer> findTypeCustomerById(String id);
	
	Mono<TypeCustomer> SaveTypeCustomer(TypeCustomer typeCustomer);
	
}
