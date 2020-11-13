package com.managedCare.salesOperation.enquiry.Common.Response;

import java.sql.Timestamp;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data

public class LeadCommonResponse {
	
	private int value;
	private String label;
	//private int id;
	//private String leadName;
	//private boolean active;
	//private Timestamp createdAt;

}
