package com.managedCare.salesOperation.enquiry.Request;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.springframework.stereotype.Component;

import com.managedCare.salesOperation.enquiry.Entity.AssignedBy;
import com.managedCare.salesOperation.enquiry.Entity.CallReceivedBy;
import com.managedCare.salesOperation.enquiry.Entity.CreateContact;
import com.managedCare.salesOperation.enquiry.Entity.EnquiryType;
import com.managedCare.salesOperation.enquiry.Entity.LanguageDetails;
import com.managedCare.salesOperation.enquiry.Entity.RecommdationDetails;
import com.managedCare.salesOperation.enquiry.Entity.Referral;
import com.managedCare.salesOperation.enquiry.Entity.Relationship;
import com.managedCare.salesOperation.enquiry.Entity.ServicesReferred;

import lombok.Data;

@Component
@Data
public class EnquiryRequestDTO {
	private int enquiryId;
	private String title;
	private String name;
	private String code;
	private String mobNo;
	private String address;
	private Timestamp createdDate;
	private CreateContact contactAdded;
	private Relationship relationship;
	private Referral referral;
	private LanguageDetails languageDetails;
	//private AssignedBy assignedBy;
   // private RecommdationDetails recommdationDetails;
	//private CallReceivedBy callReceivedBy;
	//private ServicesReferred servicesReferred;
	private String complain;
	private String medicalHistory;
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
