package com.managedCare.salesOperation.enquiry.Entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "tb_referral")
public class Referral {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "referral_id")
	private int referralId;
	@Column(name = "name", nullable = true)
	private String name;
//  new field is added as changes
	@Column(name = "is_active", nullable = true)
	private boolean active;
	
	@Column(name = "created_at", nullable = true)
	private Timestamp createdDate;
	
	
	/*
	 * @Column(name = "crea", nullable = true) private String creadtedBy;
	 * 
	 * @Column(name = "created", nullable = true) private Timestamp createdDate;
	 * 
	 * @Column(name = "UPDATED_BY", nullable = true) private String updatedBy;
	 * 
	 * @Column(name = "UPDATED_DATE", nullable = true) private Timestamp
	 * updatedDate;
	 * 
	 * @OneToOne(mappedBy = "referral", fetch = FetchType.EAGER) private
	 * EnquiryDetails enquiry;
	 */

}