package com.managedCare.salesOperation.enquiry.Common.Response;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class ReferralCommonResponse {
	
	
	//private int referralId;
	private String name;
//  new field is added as changes
	private boolean active;
	private Timestamp createdDate;
	

}
