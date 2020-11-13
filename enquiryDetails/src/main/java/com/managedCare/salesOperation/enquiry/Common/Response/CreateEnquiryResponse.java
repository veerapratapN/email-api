package com.managedCare.salesOperation.enquiry.Common.Response;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.managedCare.salesOperation.enquiry.Entity.AddEnquiryContact;

import lombok.Data;

@Component
@Data
public class CreateEnquiryResponse {
	private int enquiryId;
	private String height;
	private String weight;
	private List<String> activities;
	private Timestamp enquired_on;
	private int lead_type_id;
	private int received_id;
	private int assigned_to;
	private int source_of_referreal;
	private String complain;
	private String medicalHistory;
	private boolean doctor;
	private String enquiry_status;
	//private String mobNo;
	//private String address;
	private String adl;
	private AddEnquiryContact contact_information;

}
