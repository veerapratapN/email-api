package com.managedCare.salesOperation.enquiry.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "tb_services_referred")
public class ServicesReferred {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "services_referred_id")
	private int serviceReferredId;
	@Column(name = "Services_Referred_Name", nullable = false)
	private String serviceRferredName;
}
