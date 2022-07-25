package com.bootcamp.customer.api;

import com.bootcamp.customer.dto.CustomerRequestDto;
import com.bootcamp.customer.dto.CustomerResponseDto;
import com.bootcamp.customer.excepcion.CustomerCreationException;
import com.bootcamp.customer.model.document.Customer;
import com.bootcamp.customer.model.service.CustomerService;
import com.mongodb.client.model.ReturnDocument;

import ch.qos.logback.core.joran.conditional.IfAction;
import lombok.experimental.var;
import lombok.extern.slf4j.Slf4j;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/customers")
public class CustomerController {
	@Autowired
	private CustomerService customerService;

	@GetMapping
	public Mono<ResponseEntity<Flux<Customer>>> getAllCustomers() {
		return Mono.just(ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(customerService.getAll()));
	}

	@PostMapping
	public  Mono<ResponseEntity<Customer>> register(@Valid @RequestBody Customer customer) {
		
		return customerService.save(customer).map(p-> ResponseEntity
				.created(URI.create("/customers/".concat(p.getDocumentNumber())))
				.contentType(MediaType.APPLICATION_JSON)
				.body(p)
				);
	}

	@GetMapping("{documentNumber}")
	public Mono<ResponseEntity<Customer>> getCustomerById(@PathVariable String documentNumber) {
		return customerService.findById(documentNumber)
				.map(p-> ResponseEntity.ok()
						.contentType(MediaType.APPLICATION_JSON)
						.body(p)
						).defaultIfEmpty(ResponseEntity.notFound().build());
	}

	@DeleteMapping("{documentNumber}")
	public Mono<Void> deleteCustomer(@PathVariable String documentNumber) {
		return customerService.delete(documentNumber);
	}

	@PutMapping("{documentNumber}")
	public Mono<Customer> updateCustomer(@PathVariable String documentNumber, @RequestBody Customer customer) {
		return customerService.update(documentNumber, customer);
	}
}
