package com.managedCare.salesOperation.enquiry.Entity;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.Getter;

@Entity
@Data
@Getter
@Table(name = "tb_enquiry_details")
public class GetEnquiry {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "enquiry_id", nullable = false)
	private int enquiryId;
	@Column(name = "enquired_on")
	private Timestamp enquired_on;
	private int lead_type_id;
	private int received_id;
    private int assigned_to;
	private int source_of_referreal;
	@Column(name = "complaint")
	private String complain;
	@Column(name = "medical_history")
	private String medicalHistory;
	@Column(name = "seen_doctor")
	private boolean seenDoctor;
	@Column(name = "patient_height")
	private String height;
	@Column(name = "patient_weight")
	private String weight;
	@Column(name = "enquiry_status")
	private String enquiry_status;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "caller_id")
	private AddEnquiryContact contact_information;

	@Column(name = "activities", nullable = true)
	private String activities;
	@Column(name = "adl", nullable = true)
	private String adl;
	
	/*
	 * @Column(name = "caller_id", nullable = true) private int caller_id;
	 */

	
	/*
	 * @Column(name = "Mobile_Number", nullable = true) private String mobNo;
	 */
	/*
	 * @Column(name = "Address", nullable = false) private String address;
	 */

	/*
	 * @OneToOne(cascade = CascadeType.ALL, targetEntity = AssignedBy.class)
	 * 
	 * @JoinColumn(name = "tb_assigned_id") private AssignedBy assignedBy;
	 */

	/*
	 * @OneToOne(cascade = CascadeType.ALL)
	 * 
	 * @JoinColumn(name = "tb_referral_id") private Referral referral;
	 */

	

	
}