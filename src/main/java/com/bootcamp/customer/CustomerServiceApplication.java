package com.bootcamp.customer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

import com.bootcamp.customer.model.document.TypeCustomer;
import com.bootcamp.customer.model.service.CustomerService;

import reactor.core.publisher.Flux;

@SpringBootApplication
public class CustomerServiceApplication implements CommandLineRunner {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private ReactiveMongoTemplate mongoTemplate;

	private static final Logger log = LoggerFactory.getLogger(CustomerServiceApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		mongoTemplate.dropCollection("typecustomer").subscribe();

		TypeCustomer business = new TypeCustomer("business");
		TypeCustomer personnel = new TypeCustomer("personnel");

		Flux.just(business, personnel).flatMap(customerService::SaveTypeCustomer);

	}

}

//@RefreshScope
//@RestController
//class MessageRestController {
//
//    @Value("${service.name}")
//    private String name;
//
//    @RequestMapping("/name")
//    String getMessage() {
//        return this.name;
//    }
//}
