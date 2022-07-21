package com.bootcamp.customer.api;

import com.bootcamp.customer.model.document.Customer;
import com.bootcamp.customer.model.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping
    public Mono<Customer> register(@RequestBody Customer customer){
        return customerService.save(customer);
    }

    @GetMapping
    public Flux<Customer> getAllCustomers(){
        return customerService.getAll();
    }

    @GetMapping("{documentNumber}")
    public Mono<Customer> getCustomerById(@PathVariable String documentNumber){
        return customerService.findById(documentNumber);
    }

    @DeleteMapping("{documentNumber}")
    public Mono<Void> deleteCustomer(@PathVariable String documentNumber){
        return customerService.delete(documentNumber);
    }

    @PutMapping("{documentNumber}")
    public Mono<Customer> updateCustomer(@PathVariable String documentNumber, @RequestBody Customer customer){
        return  customerService.update(documentNumber,customer);
    }
}
