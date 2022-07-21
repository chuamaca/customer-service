package com.bootcamp.customer.service;

import com.bootcamp.customer.model.document.Customer;
import com.bootcamp.customer.model.repository.CustomerRepository;
import com.bootcamp.customer.model.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@Validated
public class CustomerServiceImpl implements CustomerService {

    private static final String NO_FOUND_MSG="Customer no found";
    private static final String NO_FOUND_MSG_WITH_ID="Customer no found id: {}";
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Flux<Customer> getAll() {
        return this.customerRepository.findAll();
    }
    @Override
    public Mono<Customer> save(Customer customer) {
        return this.customerRepository.save(customer);
    }

    @Override
    public Mono<Customer> findById(String documentNumber) {
        return this.customerRepository.findById(documentNumber)
                .doOnError(ex-> log.error(NO_FOUND_MSG_WITH_ID, documentNumber, ex));
    }

    @Override
    public Mono<Boolean> existById(String documentNumber) {
        return this.customerRepository.existsById(documentNumber);
    }

    @Override
    public Mono<Void> delete(String documentNumber) {
        return this.customerRepository.findById(documentNumber)
                .flatMap(existingCustomer-> customerRepository.delete(existingCustomer));
    }

    @Override
    public Mono<Customer> update(String documentNumber,Customer customer) {
        return this.customerRepository.findById(documentNumber)
                .flatMap(existingCustomer-> customerRepository.save(customer));
    }

}
