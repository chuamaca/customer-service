package com.bootcamp.customer.service;

import com.bootcamp.customer.excepcion.CustomerCreationException;
import com.bootcamp.customer.model.document.Customer;
import com.bootcamp.customer.model.document.TypeCustomer;
import com.bootcamp.customer.model.repository.CustomerRepository;
import com.bootcamp.customer.model.service.CustomerService;
import lombok.extern.slf4j.Slf4j;

import org.apache.logging.log4j.message.Message;
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
    	return this.customerRepository.findById(customer.getDocumentNumber())
    			.hasElement()
    			.flatMap(exist -> {
    				if (exist) return Mono.error(new CustomerCreationException("Customer already exists IF"));
    				return customerRepository.save(customer);
    			})
    			.doOnSuccess(c -> log.info("Created new customer with ID: {}", c.getDocumentNumber()))
                .doOnError(ex -> log.error("Error creating new client ", ex));
    }

    @Override
    public Mono<Customer> findById(String documentNumber) {
        return this.customerRepository.existsById(documentNumber)
        		.flatMap(rta -> {
        			if(rta) return this.customerRepository.findById(documentNumber);
					return Mono.error(new CustomerCreationException("Customer Dont exists "));
        		})
        		.doOnSuccess(c -> log.info("Created new customer with ID: {}", c.getDocumentNumber()))
                .doOnError(ex -> log.error("Error creating new client ", ex));
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
    
	@Override
	public Flux<TypeCustomer> findAllTypeCustomer(TypeCustomer typeCustomer) {
		return this.findAllTypeCustomer(typeCustomer);
	}
	
	@Override
	public Mono<TypeCustomer> findTypeCustomerById(String id) {
		return this.findTypeCustomerById(id);
	}
	
	@Override
	public Mono<TypeCustomer> SaveTypeCustomer(TypeCustomer typeCustomer) {
		return null;
	}

}
