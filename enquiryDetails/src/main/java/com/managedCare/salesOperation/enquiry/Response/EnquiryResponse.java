package com.managedCare.salesOperation.enquiry.Response;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.springframework.stereotype.Component;

import com.managedCare.salesOperation.enquiry.Entity.AddEnquiryContact;
import com.managedCare.salesOperation.enquiry.Entity.EnquiryType;

import lombok.Data;

@Component
@Data
public class EnquiryResponse {

	private int enquiryId;
	private Timestamp enquired_on;
	
	private LeadType lead_type;
	
	private CallReceivedBy call_received;
	private AssignedTo assigned_To;
	private SourceOfReferral source_of_referreal;
	
	
	//private int lead_type_id;
	//private int received_id;
	//private int assigned_to;
	//private int source_of_referreal;

	private String complain;
	private String medicalHistory;
	private boolean seenDoctor;
	private String height;
	private String weight;
	private String enquiry_status;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "caller_id")
	private AddEnquiryContact contact_information;

	@Column(name = "activities", nullable = true)
	private String activities;
	@Column(name = "adl", nullable = true)
	private String adl;

}
