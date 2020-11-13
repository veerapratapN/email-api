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
@Table(name = "tb_recommdation")
public class RecommdationDetails {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "recommdation_id")
	private int recommdationId;
	@Column(name = "Recommdation_Name", nullable = false)
	private String recommdationName;
	@Column(name = "Type", nullable = false)
	private String type;
	
	

}
