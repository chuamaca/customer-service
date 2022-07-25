package com.bootcamp.customer.model.document;

import lombok.*;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;



import java.util.Date;


import javax.validation.constraints.NotEmpty;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "customers")
public class Customer {

	@Indexed(unique = true)
	@Id
	@NotEmpty(message = "The Document Must Be Unique")
	private String documentNumber;

	@NotEmpty(message = "The Type of Doc. Can be DNI|CE|PASSPORT")
	private String documentType;

	@NotEmpty(message = "The FirstName is required")
	private String firstName;

	@NotEmpty(message = "The LastName is required")
	private String lastName;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createAt;
	
	private TypeCustomer type;
	

}
