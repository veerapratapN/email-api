package com.managedCare.salesOperation.enquiry.Common.Response;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class AssignedCommonResponse {
	
	
	//private long carecoordinator_id;
	
	private int mobile_no_isdcode;
	private String availability_status;
	private String address;
	private String carecoordinator_name;
	private String email_id;
	private Timestamp created_date;
    private Timestamp modified_date;
	private long mobile_no;
    private int caregivers_count;
	private int clients_count;
	private int upload_photo_id;

}
