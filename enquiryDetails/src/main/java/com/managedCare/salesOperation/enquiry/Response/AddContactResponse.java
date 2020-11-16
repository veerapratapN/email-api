package com.managedCare.salesOperation.enquiry.Response;

import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class AddContactResponse {
	private int contactId;
	private String salutation;
	private String name;
	private Date date_of_birth;
	private String gender;
	private String mobNo;
	private String address;
	private String email;
	
}
