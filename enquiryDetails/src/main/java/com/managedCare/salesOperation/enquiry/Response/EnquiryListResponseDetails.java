package com.managedCare.salesOperation.enquiry.Response;

import java.sql.Timestamp;

import org.springframework.stereotype.Component;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data

public class EnquiryListResponseDetails {
	private String tag;
	//private int id;
	//private Timestamp raisedOn;
	private int id;
	private String raised_on;
	private String name;
	private String status;
	//private int leadTime;
	private String lead_Time;
}
