package com.managedCare.salesOperation.enquiry.Response;

import javax.persistence.Column;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class AssignedTo {
	
	private long value;
	private String label;
	
	//private long carecoordinator_id;
	//private String carecoordinator_name;
	
	/*
	 * private int id; private String role; private String name;
	 */

}
