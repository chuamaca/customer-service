package com.bootcamp.customer.dto;

import java.util.Date;

import com.bootcamp.customer.model.document.Customer;
import com.bootcamp.customer.model.document.TypeCustomer;

import lombok.*;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponseDto {

	private String documentNumber;

	private String documentType;

	private String firstName;

	private String lastName;

	private Date createAt;

	private TypeCustomer type;
	
	
	public static CustomerResponseDto fromModel(Customer customer) {
		
		return CustomerResponseDto.builder()
				.documentNumber(customer.getDocumentNumber())
				.documentType(customer.getDocumentType())
				.firstName(customer.getFirstName())
				.lastName(customer.getLastName())
				.createAt(customer.getCreateAt())
				.type(customer.getType())
				.build();
		  
	}
}
