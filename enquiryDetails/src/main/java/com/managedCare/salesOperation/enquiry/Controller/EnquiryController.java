package com.managedCare.salesOperation.enquiry.Controller;

import java.sql.Timestamp;

import java.util.ArrayList;


import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.managedCare.salesOperation.enquiry.Common.Response.AssignedCommonResponse;
import com.managedCare.salesOperation.enquiry.Common.Response.AssignedResponse;
import com.managedCare.salesOperation.enquiry.Common.Response.CommonResponseInsert;
import com.managedCare.salesOperation.enquiry.Common.Response.ContactCommonResponse;
import com.managedCare.salesOperation.enquiry.Common.Response.ContactResponce;

import com.managedCare.salesOperation.enquiry.Common.Response.CreateEnquiryCommonResponse;
import com.managedCare.salesOperation.enquiry.Common.Response.CreateEnquiryResponse;
import com.managedCare.salesOperation.enquiry.Common.Response.LeadCommonResponse;
import com.managedCare.salesOperation.enquiry.Common.Response.LeadTypeResponse;
import com.managedCare.salesOperation.enquiry.Common.Response.ReferralCommonResponse;
import com.managedCare.salesOperation.enquiry.Common.Response.ReferralResponse;
import com.managedCare.salesOperation.enquiry.Entity.CareCoordinator;
import com.managedCare.salesOperation.enquiry.Entity.CreateContact;
import com.managedCare.salesOperation.enquiry.Entity.LeadType;
import com.managedCare.salesOperation.enquiry.Entity.Referral;
import com.managedCare.salesOperation.enquiry.Request.AssignedByDto;

import com.managedCare.salesOperation.enquiry.Entity.CreateContact;

import com.managedCare.salesOperation.enquiry.Request.ContactDetailsDTO;
import com.managedCare.salesOperation.enquiry.Request.EnquiryEditRequestDTO;
import com.managedCare.salesOperation.enquiry.Request.EnquiryLeadTypeDto;
import com.managedCare.salesOperation.enquiry.Request.EnquiryReferralDto;
import com.managedCare.salesOperation.enquiry.Request.EnquiryRequestDTO;
import com.managedCare.salesOperation.enquiry.Response.EnquiriesResponseDto;
import com.managedCare.salesOperation.enquiry.Response.EnquiryResponseDTO;
import com.managedCare.salesOperation.enquiry.Response.EnquiryStatusResponse;
import com.managedCare.salesOperation.enquiry.Service.EnquiryDetailService;

@RestController
@RequestMapping("qdm/enquiry/master")
public class EnquiryController {

	@Autowired
	private EnquiryDetailService enquiryDetail;
	@Autowired
	private CommonResponseInsert commonResponse;
	@Autowired
	private ContactCommonResponse commonResponse1;

	@Autowired
	CreateEnquiryCommonResponse commonResponse2;

	@PostMapping("/getAllLeadType")
	public ResponseEntity<?> getAllLeadType() {
		EnquiryLeadTypeDto leadDTO =new EnquiryLeadTypeDto();
		List<LeadType> lead = enquiryDetail.getAllLeadType(leadDTO);

		LeadTypeResponse leadTypeResponse = new LeadTypeResponse();

		leadTypeResponse.setStatusCode(HttpStatus.OK);
		leadTypeResponse.setStatus("Success");
		leadTypeResponse.setMessage("Successfully Get LeadTypeResponse");

		List<LeadCommonResponse> list = new ArrayList<>();
		
		for(LeadType cm:lead ) {
			LeadCommonResponse commonResponse = new LeadCommonResponse();
			commonResponse.setValue(cm.getId());
			commonResponse.setLabel(cm.getLeadName());
			list.add(commonResponse);
			
			
		}

		
		
		
		//commonResponse.setActive(lead.isActive());
		//commonResponse.setCreatedAt(lead.getCreatedAt());
		

		leadTypeResponse.setData(list);

		return new ResponseEntity<LeadTypeResponse>(leadTypeResponse, HttpStatus.OK);

	}

	@PostMapping("/getAllAssignedTo")
	public ResponseEntity<?> getAllAssignedTo() {
		AssignedByDto assignDTO=new AssignedByDto();
		List<CareCoordinator> care = enquiryDetail.getAllAssignedBy(assignDTO);

		AssignedResponse response = new AssignedResponse();

		response.setStatusCode(HttpStatus.OK);
		response.setStatus("Success");
		response.setMessage("Successfully Get AssignToResponse ");

		List<AssignedCommonResponse> list = new ArrayList<>();
		for(CareCoordinator cc:care) {
			
			AssignedCommonResponse assignRes = new AssignedCommonResponse();
			
			assignRes.setValue(cc.getCarecoordinator_id());
			assignRes.setLabel(cc.getCarecoordinator_name());
			

			list.add(assignRes);
			
		}

		

		response.setData(list);

		return new ResponseEntity<AssignedResponse>(response, HttpStatus.OK);

	}

	@PostMapping("/getAllSourceOfReferral")
	public ResponseEntity<?> getAllSourceOfReferral() {
		EnquiryReferralDto rferralDTO =new EnquiryReferralDto();
		List<Referral> source = enquiryDetail.getAllSourceOfRefferal(rferralDTO);

		ReferralResponse referralResponse = new ReferralResponse();

		referralResponse.setStatusCode(HttpStatus.OK);
		referralResponse.setStatus("Success");
		referralResponse.setMessage("Successfully Get SourceOf Referral ");

		List<ReferralCommonResponse> referralList = new ArrayList<>();
		
		for(Referral ref:source) {
		ReferralCommonResponse commonResponse = new ReferralCommonResponse();
		
		commonResponse.setValue(ref.getReferralId());
		commonResponse.setLabel(ref.getName());
		referralList.add(commonResponse);
		}

		referralResponse.setData(referralList);

		return new ResponseEntity<ReferralResponse>(referralResponse, HttpStatus.OK);

	}


	@PostMapping("/enquiry/add")
	public ResponseEntity<?> addEnquiry(@RequestBody EnquiryRequestDTO enquiryRequest) {
		int enquiryId = enquiryDetail.createEnquiry(enquiryRequest);
		if (enquiryId != 0) {

			CreateEnquiryResponse data = new CreateEnquiryResponse();
			data.setEnquiryId(enquiryId);
			data.setActivities(enquiryRequest.getActivities());
			//data.setAddress(enquiryRequest.getAddress());
			data.setAdl(enquiryRequest.getAdl());
			data.setAssigned_to(enquiryRequest.getAssigned_to());
			data.setComplain(enquiryRequest.getComplain());
			data.setContact_information(enquiryRequest.getContact_information());
			data.setDoctor(enquiryRequest.isDoctor());
			data.setEnquired_on(enquiryRequest.getEnquired_on());
			data.setEnquiry_status(enquiryRequest.getEnquiry_status());
			data.setHeight(enquiryRequest.getHeight());
			data.setWeight(enquiryRequest.getWeight());
			data.setLead_type_id(enquiryRequest.getLead_type_id());
			data.setReceived_id(enquiryRequest.getReceived_id());
			data.setSource_of_referreal(enquiryRequest.getSource_of_referreal());
			data.setMedicalHistory(enquiryRequest.getMedicalHistory());
			//data.setMobNo(enquiryRequest.getMobNo());
			commonResponse2.setData(data);
			commonResponse2.setMessage("Successfully Created");
			commonResponse2.setStatusCode(HttpStatus.CREATED);
			commonResponse2.setStatus("Success");

		}
		return new ResponseEntity<CreateEnquiryCommonResponse>(commonResponse2, HttpStatus.CREATED);
	}

	/*@PostMapping("/entity/add")
	public ResponseEntity<CommonResponseInsert> addEnquiry(@RequestBody EnquiryRequestDTO enquiryRequest) {
		enquiryDetail.createEnquiry(enquiryRequest);
		commonResponse.setData("");
		commonResponse.setMessage("Successfully Created");
		commonResponse.setStatusCode(HttpStatus.CREATED);
		commonResponse.setStatus("Success");
		return new ResponseEntity<CommonResponseInsert>(commonResponse, HttpStatus.CREATED);

	}*/

	@PutMapping("/entity/edit/{id}")
	public ResponseEntity<CommonResponseInsert> editEnquiry(@PathVariable("id") int id,
			@RequestBody EnquiryEditRequestDTO enquiryRequest) {
		// enquiryDetail.editEnquery(id, enquiryRequest);
		commonResponse.setData(null);
		commonResponse.setMessage("Successfully Updated");
		commonResponse.setStatusCode(HttpStatus.ACCEPTED);
		commonResponse.setStatus("Success");
		return new ResponseEntity<CommonResponseInsert>(commonResponse, HttpStatus.ACCEPTED);
	}

	@GetMapping("/customerSupport/getAllFirstName")
	public ResponseEntity<?> getAllcustomerSupportFirstName() {
		List<String> firstNameCustomerSupport = null;

		firstNameCustomerSupport = enquiryDetail.getAllCustomerSupportFirstName();

		if (firstNameCustomerSupport.isEmpty()) {

			CommonResponseInsert commonError = new CommonResponseInsert();
			commonError.setStatus("list is empty");
			commonError.setStatusCode(HttpStatus.BAD_REQUEST);
			commonError.setMessage("No Records Found");
			return new ResponseEntity(commonError, HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<List<String>>(firstNameCustomerSupport, HttpStatus.OK);
		}
	}

	@GetMapping("/customerSupport/assign/getAllFirstName")
	public ResponseEntity<List<String>> getAllassignedToFirstName() {
		List<String> firstNameCustomerSupport = null;
		firstNameCustomerSupport = enquiryDetail.getAllassignedToFirstName();

		if (firstNameCustomerSupport.isEmpty()) {
			CommonResponseInsert commonError = new CommonResponseInsert();
			commonError.setStatus("list is empty");
			commonError.setStatusCode(HttpStatus.BAD_REQUEST);
			commonError.setMessage("No Records Found");
			return new ResponseEntity(commonError, HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<List<String>>(firstNameCustomerSupport, HttpStatus.OK);
		}
	}

	@PostMapping("/customerSupport/add/contact")
	public ResponseEntity<ContactCommonResponse> addContact(@RequestBody ContactDetailsDTO contactDetails) {
		int idCreated = enquiryDetail.createContact(contactDetails);

		if (idCreated != 0) {

			commonResponse1.setMessage("Successfully Added Contact Id : " + idCreated);
			ContactResponce contactResponce = new ContactResponce();
			contactResponce.setCallerId(idCreated);
			contactResponce.setTitle(contactDetails.getTitle());
			contactResponce.setCallerName(contactDetails.getCallerName());
			contactResponce.setCode(contactDetails.getCode());
			contactResponce.setCreatedBy(contactDetails.getCreatedBy());
			contactResponce.setCreatedDate(contactDetails.getCreatedDate());
			contactResponce.setCurrentAddress(contactDetails.getCurrentAddress());
			contactResponce.setPermanentAddress(contactDetails.getPermanentAddress());
			contactResponce.setDob(contactDetails.getDob());
			contactResponce.setGender(contactDetails.getGender());
			contactResponce.setMobNumber(contactDetails.getMobNumber());
			contactResponce.setLanguage(contactDetails.getLanguage());
			Date date = new Date();
			Timestamp currentTimeStamp = new Timestamp(date.getTime());
			contactResponce.setCreatedDate(currentTimeStamp); // contactDetails.getCreatedDate()
			contactResponce.setUpdatedBy(contactDetails.getUpdatedBy());
			contactResponce.setUpdatedDate(currentTimeStamp); // contactDetails.getUpdatedDate()

			commonResponse1.setData(contactResponce);
			commonResponse1.setError("");
			commonResponse1.setStatusCode(HttpStatus.CREATED);
			commonResponse1.setStatus("Success");


			

		} else {
			commonResponse1.setError("Request Failed");
		}
		return new ResponseEntity<ContactCommonResponse>(commonResponse1, HttpStatus.CREATED);
	}

	@GetMapping("/customerSupport/getFullNamewithSal")
	public ResponseEntity<List<String>> getFullNamewithSal() {
		List<String> fullName = null;
		fullName = enquiryDetail.getFullNameWithTitle();
		if (fullName.isEmpty()) {

			CommonResponseInsert commonError = new CommonResponseInsert();
			commonError.setStatus("list is empty");
			commonError.setStatusCode(HttpStatus.BAD_REQUEST);
			commonError.setMessage("No Records Found");
			return new ResponseEntity(commonError, HttpStatus.BAD_REQUEST);
		} else {

			return new ResponseEntity<List<String>>(fullName, HttpStatus.OK);
		}
	}

	@GetMapping("/referral/getALLName")
	public ResponseEntity<List<String>> getAllReferralName() {
		List<String> referralName = null;
		referralName = enquiryDetail.getAllReferralName();

		if (referralName.isEmpty()) {

			CommonResponseInsert commonError = new CommonResponseInsert();
			commonError.setStatus("list is empty");
			commonError.setStatusCode(HttpStatus.BAD_REQUEST);
			commonError.setMessage("No Records Found");
			return new ResponseEntity(commonError, HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<List<String>>(referralName, HttpStatus.OK);
		}
	}

	@GetMapping("/customerSupport/getAllRelationship")
	public ResponseEntity<List<String>> getAllRelationship() {
		List<String> relationship = null;
		relationship = enquiryDetail.getAllRelationship();
		if (relationship.isEmpty()) {

			CommonResponseInsert commonError = new CommonResponseInsert();
			commonError.setStatus("list is empty");
			commonError.setStatusCode(HttpStatus.BAD_REQUEST);
			commonError.setMessage("No Records Found");
			return new ResponseEntity(commonError, HttpStatus.BAD_REQUEST);
		} else {

			return new ResponseEntity<List<String>>(relationship, HttpStatus.OK);
		}
	}

	@GetMapping(value = "/entity/get/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<EnquiriesResponseDto> getEnquiry(@PathVariable("id") int id, EnquiriesResponseDto response) {
		try {
			EnquiriesResponseDto enquiryResponse = enquiryDetail.getEnquiryInfoById(id, response);
			return new ResponseEntity<EnquiriesResponseDto>(enquiryResponse, HttpStatus.OK);
		} catch (NullPointerException ex) {
			CommonResponseInsert commonError = new CommonResponseInsert();
			commonError.setStatusCode(HttpStatus.BAD_REQUEST);
			commonError.setMessage("No Records Found");
			return new ResponseEntity(commonError, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/customerSupport/getAllEnquiryType")
	public ResponseEntity<List<String>> getAllEnquiryType() {
		List<String> enquiryType = null;
		enquiryType = enquiryDetail.getAllEnquiryTypeName();
		if (enquiryType.isEmpty()) {

			CommonResponseInsert commonError = new CommonResponseInsert();
			commonError.setStatus("list is empty");
			commonError.setStatusCode(HttpStatus.BAD_REQUEST);
			commonError.setMessage("No Records Found");
			return new ResponseEntity(commonError, HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<List<String>>(enquiryType, HttpStatus.OK);
		}
	}

	@PostMapping("/enquiry/getViewAll")
	public ResponseEntity<EnquiryStatusResponse> getViewAllEnquiry(
			@RequestParam(defaultValue = "0", required = false) Integer pageNo,
			@RequestParam(defaultValue = "10", required = false) Integer pageSize) {
		EnquiryStatusResponse enquiry = enquiryDetail.getAllEnquiry(pageNo, pageSize);

		if (enquiry == null) {

			CommonResponseInsert commonError = new CommonResponseInsert();
			commonError.setStatus("list is empty");
			commonError.setStatusCode(HttpStatus.BAD_REQUEST);
			commonError.setMessage("No Records Found");
			return new ResponseEntity(commonError, HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<EnquiryStatusResponse>(enquiry, HttpStatus.OK);
		}
	}
}
