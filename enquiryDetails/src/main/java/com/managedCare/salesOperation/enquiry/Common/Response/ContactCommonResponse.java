
/*package com.managedCare.salesOperation.enquiry.Common.Response;*/

/*import java.sql.Timestamp;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import lombok.Data;*/

/*@Component
@Data
public class ContactCommonResponse {
	private String status;
	private HttpStatus statusCode;
	private String message;
	private ContactResponce data;
	private String error;

}*/
package com.managedCare.salesOperation.enquiry.Common.Response;

import java.sql.Timestamp;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class ContactCommonResponse {
	private String status;
	private HttpStatus statusCode;
	private String message;
	private ContactResponce data;
	private String error;

}

