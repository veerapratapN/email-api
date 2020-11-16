package com.managedCare.salesOperation.enquiry.objectmapping;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import com.managedCare.salesOperation.enquiry.Entity.EnquiryDetailList;
import com.managedCare.salesOperation.enquiry.Entity.GetEnquiry;
import com.managedCare.salesOperation.enquiry.Response.ActivityOfDailyLiving;
import com.managedCare.salesOperation.enquiry.Response.AssignedTo;
import com.managedCare.salesOperation.enquiry.Response.CallInformation;
import com.managedCare.salesOperation.enquiry.Response.CallReceivedBy;
import com.managedCare.salesOperation.enquiry.Response.CallerInformation;
import com.managedCare.salesOperation.enquiry.Response.ContactInformation;
import com.managedCare.salesOperation.enquiry.Response.Enquiries_Type;
import com.managedCare.salesOperation.enquiry.Response.EnquiryListResponseDetails;
import com.managedCare.salesOperation.enquiry.Response.EnquiryStatusResponse;
import com.managedCare.salesOperation.enquiry.Response.Recommendation;
import com.managedCare.salesOperation.enquiry.Response.SeenDoctor;
import com.managedCare.salesOperation.enquiry.Response.ServiceReferred;
import com.managedCare.salesOperation.enquiry.Response.SourceOfReferral;
import com.managedCare.salesOperation.enquiry.Response.Status;
import com.managedCare.salesOperation.enquiry.Response.VerbalAssessment;

@Component
public class ManagedCareBO {

	@PersistenceContext
	private EntityManager em;

	

	public EnquiryStatusResponse mapService(List<EnquiryDetailList> list, int pageNo) {
		Integer statusOpenCount = 0;
		int statusPendingCount = 0;
		int statusClosedCount = 0;
		EnquiryStatusResponse objServicesList = new EnquiryStatusResponse();
		List<Status> stats = new ArrayList<Status>();
		stats.clear();
		List<EnquiryListResponseDetails> listEnquiry = new ArrayList<EnquiryListResponseDetails>();
		for (EnquiryDetailList beanEnquiry : list) {
			EnquiryListResponseDetails details = new EnquiryListResponseDetails();
			
			int enquiryId = beanEnquiry.getEnquiryId();
			Integer enquiryIdObj = new Integer(enquiryId);
			String enquireId = enquiryIdObj.toString();
			String completeEnquire = "#ENQ" + enquireId;
			Timestamp createdDate = beanEnquiry.getCreatedDate();
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss:aa");
			String created = dateFormat.format(createdDate);
			details.setTag(completeEnquire);
			details.setId(enquiryId);
			details.setRaised_on(created);
			details.setId(enquiryId);
			details.setName(beanEnquiry.getName());
			String status = beanEnquiry.getStatus();
			details.setStatus(status);
			// details.setLeadTime(0);

			int lead = beanEnquiry.getLeadTime();
			// int lead = details.getLeadTime();
			Integer l = new Integer(lead);
			String lead_Time = l.toString();
			details.setLead_Time(lead_Time + " Hours Lead Time");
			if (status.equalsIgnoreCase("Open")) {
				statusOpenCount++;
			} else if (status.equalsIgnoreCase("Pending")) {
				statusPendingCount++;
			} else if (status.equalsIgnoreCase("Closed")) {
				statusClosedCount++;
			}
			listEnquiry.add(details);
		}
		int leadTime = 0;
		Status strutsOpenEnquiry = new Status();
		strutsOpenEnquiry.setLabel("Open");
		strutsOpenEnquiry.setCount(statusOpenCount);
		Status strutsClosedEnquiry = new Status();
		strutsClosedEnquiry.setLabel("Closed");
		strutsClosedEnquiry.setCount(statusClosedCount);
		Status strutsPendingEnquiry = new Status();
		strutsPendingEnquiry.setLabel("Pending");
		strutsPendingEnquiry.setCount(statusPendingCount);
		Status statusLeadTime = new Status();
		statusLeadTime.setLabel("Average Lead Time");
		statusLeadTime.setCount(leadTime);
		stats.add(strutsOpenEnquiry);
		stats.add(strutsClosedEnquiry);
		stats.add(strutsPendingEnquiry);
		stats.add(statusLeadTime);
		objServicesList.setStats(stats);
		objServicesList.setList(listEnquiry);
		return objServicesList;

	}

}
