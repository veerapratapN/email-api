package com.managedCare.salesOperation.enquiry.Service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.managedCare.salesOperation.enquiry.Entity.CareCoordinator;
import com.managedCare.salesOperation.enquiry.Entity.CreateContact;
import com.managedCare.salesOperation.enquiry.Entity.LeadType;
import com.managedCare.salesOperation.enquiry.Entity.Referral;
import com.managedCare.salesOperation.enquiry.Request.AssignedByDto;
import com.managedCare.salesOperation.enquiry.Request.ContactDetailsDTO;
import com.managedCare.salesOperation.enquiry.Request.EnquiryEditRequestDTO;
import com.managedCare.salesOperation.enquiry.Request.EnquiryLeadTypeDto;
import com.managedCare.salesOperation.enquiry.Request.EnquiryReferralDto;
import com.managedCare.salesOperation.enquiry.Request.EnquiryRequestDTO;
import com.managedCare.salesOperation.enquiry.Response.EnquiriesResponseDto;
import com.managedCare.salesOperation.enquiry.Response.EnquiryResponseDTO;
import com.managedCare.salesOperation.enquiry.Response.EnquiryStatusResponse;

@Service
public interface EnquiryDetailService {

	public int createEnquiry(EnquiryRequestDTO enquiryRequest);

	public List<String> getAllCustomerSupportFirstName();

	public List<String> getAllassignedToFirstName();

	public int createContact(ContactDetailsDTO contactCreateDTO);

	public List<String> getFullNameWithTitle();

	public List<String> getAllReferralName();

	public List<String> getAllRelationship();

	//public EnquiryResponseDTO getEnquiryById(int id, EnquiryResponseDTO response);

	public EnquiriesResponseDto getEnquiryInfoById(int id, EnquiriesResponseDto response);

	List<String> getAllEnquiryTypeName();

	//public int editEnquery(int id, EnquiryEditRequestDTO enquiryRequest);

	public EnquiryStatusResponse getAllEnquiry(int pageSize, int pageNo);
	
	public List<LeadType> getAllLeadType(EnquiryLeadTypeDto leadTypeDto);

	public List<Referral> getAllSourceOfRefferal(EnquiryReferralDto referralDto);
	
	public List<CareCoordinator> getAllAssignedBy(AssignedByDto assignedByDto);

}
