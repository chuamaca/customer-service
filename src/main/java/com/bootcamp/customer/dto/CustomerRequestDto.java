package com.bootcamp.customer.dto;

import java.util.Date;
import java.util.Objects;

import com.bootcamp.customer.excepcion.CustomerCreationException;
import com.bootcamp.customer.model.document.Customer;
import com.bootcamp.customer.model.document.TypeCustomer;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequestDto {

	private String documentNumber;

	private String documentType;

	private String firstName;

	private String lastName;

	private Date createAt;

	private TypeCustomer type;
	
	
	public Customer toModel() {
		
		  if (Objects.isNull(documentType) || Objects.isNull(documentNumber) ||
	                documentType.isEmpty() || documentNumber.isEmpty())
	            throw new CustomerCreationException("DocumentType and DocumentNumber are required");
		  
		  if(documentNumber.equalsIgnoreCase(getDocumentNumber()))
			  throw new CustomerCreationException("ID document already exists");
		  
		  if (type.getType().equalsIgnoreCase("business") || type.getType().equalsIgnoreCase("personnel") )
	            throw new CustomerCreationException("Customer Type " + type.getType() +" is not valid");

	   
		
		return Customer.builder()
				.documentNumber(this.documentNumber)
				.documentType(this.documentType)
				.firstName(this.firstName)
				.lastName(this.lastName)
				.createAt(this.createAt)
				.type(this.type)
				.build();
		  
	}
}
