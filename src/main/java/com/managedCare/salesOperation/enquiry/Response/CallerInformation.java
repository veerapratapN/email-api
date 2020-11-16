package com.managedCare.salesOperation.enquiry.Response;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class CallerInformation {
	private boolean isCaller_contact_same;
	private String name;
	private String mobile;
	private String address;
	private Realationship releationship;
	

}
