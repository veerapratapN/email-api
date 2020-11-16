package com.managedCare.salesOperation.enquiry.Request;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.springframework.jca.cci.CannotCreateRecordException;
import org.springframework.stereotype.Component;

import com.managedCare.salesOperation.enquiry.Entity.AddEnquiryContact;
import com.managedCare.salesOperation.enquiry.Entity.AssignedBy;
import com.managedCare.salesOperation.enquiry.Entity.CallReceivedBy;
import com.managedCare.salesOperation.enquiry.Entity.CareCoordinator;
import com.managedCare.salesOperation.enquiry.Entity.CreateContact;
import com.managedCare.salesOperation.enquiry.Entity.EnquiryType;
import com.managedCare.salesOperation.enquiry.Entity.LanguageDetails;
import com.managedCare.salesOperation.enquiry.Entity.RecommdationDetails;
import com.managedCare.salesOperation.enquiry.Entity.Referral;
import com.managedCare.salesOperation.enquiry.Entity.Relationship;
import com.managedCare.salesOperation.enquiry.Entity.ServicesReferred;
import com.managedCare.salesOperation.enquiry.Response.LeadType;

import lombok.Data;

@Component
@Data
public class EnquiryRequestDTO {

	// private CreateContact contactInformation;
	/*
	 * private Referral referral; private CareCoordinator assignedTo; private
	 * CallReceivedBy callReceivedBy;
	 */

	//private String height;
	//private String weight;
	//private List<String> activities;

	private int enquiryId;
	private Timestamp enquired_on;
	private int lead_type_id;
	private int received_id;
	private int assigned_to;
	private int source_of_referreal;

	private String complain;
	private String medicalHistory;

	private boolean doctor;
	private String enquiry_status;

	private String mobNo;
	private String address;

	private String adl;

	private AddEnquiryContact contact_information;

	private boolean docCheck;
	private String height;
	private String weight;
	private String feeding;
	private String bathing;
	private String toileting;
	private String grooming;
	private String ambulation;
	private String transfer;
	private String bedMobilty;
	private int recommendation;
	private int servicePreferred;
	private List<String> activities;
	private String rehabCriteria;
	private String status;
	private EnquiryType enquiryType;
	private int leadTime;
	private String createdBy;
	private Timestamp closedTime;
	private String updatedBy;
	private Timestamp updatedDate;


}
