package com.managedCare.salesOperation.enquiry.Request;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.stereotype.Component;

import com.managedCare.salesOperation.enquiry.Entity.AddEnquiryContact;

import lombok.Data;

@Component
@Data
public class EnquiryEditRequestDTO {
	
	private Timestamp enquired_on;

	private int lead_type_id;
	private int received_id;
	private int assigned_to;
	private int source_of_referreal;

	private String complain;
	private String medicalHistory;
	private boolean seenDoctor;
	private String height;
	private String weight;
	private String enquiry_status;

	
	private AddEnquiryContact contact_information;
	private List<String> activities;
	//private String activities;
	private String adl;



}
