package com.managedCare.salesOperation.enquiry.Common.Response;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class CreateEnquiryCommonResponse {
	
	private String status;
	private HttpStatus statusCode;
	private String message;
	private CreateEnquiryResponse data;
	//private String error;

}
