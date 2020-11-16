package com.managedCare.salesOperation.enquiry.Entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "tb_care_coordinator")
public class CareCoordinator {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "carecoordinator_id", nullable = false)
	private long carecoordinator_id;
	@Column(name = "mobile_no_isdcode")
	private Integer mobile_no_isdcode;
	@Column(name = "availability_status")
	private String availability_status;
	@Column(name = "address")
	private String address;
	@Column(name = "carecoordinator_name")
	private String carecoordinator_name;
	@Column(name = "email_id")
	private String email_id;
	@Column(name = "created_date")
	private Timestamp created_date;
	@Column(name = "modified_date")
    private Timestamp modified_date;
	@Column(name = "mobile_no")
	private Long mobile_no;
	@Column(name = "caregivers_count")
    private Integer caregivers_count;
	@Column(name = "clients_count")
	private Integer clients_count;
	@Column(name = "upload_photo_id",nullable = true)
	private Integer upload_photo_id;
}
