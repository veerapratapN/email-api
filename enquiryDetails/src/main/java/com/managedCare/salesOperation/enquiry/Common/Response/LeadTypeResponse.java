package com.managedCare.salesOperation.enquiry.Common.Response;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class LeadTypeResponse {
	
	private String status;
	private HttpStatus statusCode;
	private String message;
	private List<LeadCommonResponse> data;
	//private String error;

}
