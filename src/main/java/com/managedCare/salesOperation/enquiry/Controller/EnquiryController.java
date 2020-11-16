package com.managedCare.salesOperation.enquiry.Controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.managedCare.salesOperation.enquiry.Common.Response.CommonResponseInsert;
import com.managedCare.salesOperation.enquiry.Common.Response.ContactCommonResponse;
import com.managedCare.salesOperation.enquiry.Common.Response.ContactResponce;
import com.managedCare.salesOperation.enquiry.Entity.QuoteAttachement;
import com.managedCare.salesOperation.enquiry.Entity.QuoteSource;
import com.managedCare.salesOperation.enquiry.Exception.RecordNotFoundException;
import com.managedCare.salesOperation.enquiry.Request.ContactDetailsDTO;
import com.managedCare.salesOperation.enquiry.Request.EnquiryEditRequestDTO;
import com.managedCare.salesOperation.enquiry.Request.EnquiryRequestDTO;
import com.managedCare.salesOperation.enquiry.Response.EnquiriesResponseDto;
import com.managedCare.salesOperation.enquiry.Response.EnquiryStatusResponse;
import com.managedCare.salesOperation.enquiry.Service.EnquiryDetailService;
import com.managedCare.salesOperation.enquiry.Service.impl.FileTransfer;
import com.managedCare.salesOperation.enquiry.Service.impl.SendEmail;

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
	FileTransfer fileTransfer;
	
	@Autowired
	private SendEmail sendemail;
	
	//naveen
	@PostMapping("/pdftransferdata")
	public ResponseEntity<QuoteAttachement> addPdf1(@RequestBody QuoteSource quoteSource) throws IOException {
		
		ResponseEntity<QuoteAttachement> responseEntity = null;
		System.out.println(quoteSource.getQuoteId()+"==="+quoteSource.getSourceFileLocation());
		fileTransfer.moveFileToDestnationLocation(quoteSource.getQuoteId(),quoteSource.getSourceFileLocation());
		
		responseEntity = new ResponseEntity<QuoteAttachement>(HttpStatus.OK);
		return responseEntity;
	}
	//update
	
	 @PutMapping(value= "/docs/update/{id}")
	    public QuoteAttachement updateEmployee(@RequestBody QuoteAttachement updatedoc, @PathVariable int id) throws Exception {
	     
	 
	        Optional<QuoteAttachement> updating =  Optional.of(fileTransfer.updateDocs(updatedoc));
	        if (!updating.isPresent())
	            throw new Exception("Could not find files with id- " + id);
	   
	        if(updatedoc.getDestnationFileLocation() == null || updatedoc.getDestnationFileLocation().isEmpty())
	        	updatedoc.setDestnationFileLocation((updating.get().getDestnationFileLocation()));;
	    
	 
	        	updatedoc.setQuoteId(id);;
	        return fileTransfer.updateDocs(updatedoc);
	    }
	 @PutMapping("/update")
	    public ResponseEntity<QuoteAttachement> createOrUpdateData(QuoteAttachement quoteattachement)
	                                                    throws RecordNotFoundException {
		 QuoteAttachement updated = fileTransfer.createOrUpdateData(quoteattachement);
	        return new ResponseEntity<QuoteAttachement>(updated, new HttpHeaders(), HttpStatus.OK);
	    } 
	
	//getbyid
	@GetMapping("/{id}")
    public ResponseEntity<QuoteAttachement> getdocById(@PathVariable("id") Integer id) 
                                                    throws RecordNotFoundException {
		QuoteAttachement entity = fileTransfer.getDocById(id);
 
        return new ResponseEntity<QuoteAttachement>(entity, new HttpHeaders(), HttpStatus.OK);
    }
	//getdocs
	@GetMapping("/getdocs")
    public ResponseEntity<List<QuoteAttachement>> findallData() {
        List<QuoteAttachement> list = fileTransfer.findallData();
 
        return new ResponseEntity<List<QuoteAttachement>>(list, new HttpHeaders(), HttpStatus.OK);
    }
 
	
	 @PostMapping("/sendmail")
	    public ResponseEntity sendMessage(@RequestParam String toMailId,String messageBody,String finalFilename){
	        ResponseEntity responseEntity;
	        try{
	        	sendemail.sendMail(toMailId,messageBody,finalFilename);
	            responseEntity = new ResponseEntity("Message send successful",HttpStatus.OK);
	        }catch (MessagingException e) {
	            responseEntity = new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
	            e.printStackTrace();
	        }
	        return responseEntity;
	    }
	

	@PostMapping("/entity/add")
	public ResponseEntity<CommonResponseInsert> addEnquiry(@RequestBody EnquiryRequestDTO enquiryRequest) {
		enquiryDetail.createEnquiry(enquiryRequest);
		commonResponse.setData("");
		commonResponse.setMessage("Successfully Created");
		commonResponse.setStatusCode(HttpStatus.CREATED);
		commonResponse.setStatus("Success");
		return new ResponseEntity<CommonResponseInsert>(commonResponse, HttpStatus.CREATED);
	}

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
