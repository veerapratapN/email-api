package com.managedCare.salesOperation.enquiry.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "tb_QuoteAttachement")
public class QuoteAttachement {
	
	@Id
	@Column(name = "quote_id")
	private int quoteId;
	
	@Column(name = "destnation_File_Location")
	private String destnationFileLocation;
	
	
	

}
