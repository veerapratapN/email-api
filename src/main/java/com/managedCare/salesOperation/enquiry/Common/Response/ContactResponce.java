package com.managedCare.salesOperation.enquiry.Common.Response;

import java.sql.Timestamp;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import lombok.Data;
@Component
@Data
public class ContactResponce {
	private int callerId;
	private String title;
	private String callerName;
	private String dob;
	private String gender;
	private String code;
	private String mobNumber;
	private String permanentAddress;
	private String currentAddress;
	private String language;
	private String createdBy;
	private Timestamp createdDate;
	private String updatedBy;
	private Timestamp updatedDate;

}
