package com.managedCare.salesOperation.enquiry.Entity;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "tb_en_contacts")
public class AddEnquiryContact {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "contact_id", nullable = false)
	private int contactId;
	@Column(name = "salutation")
	private String salutation;
	@Column(name = "contact_name")
	private String name;
	@Column(name = "date_of_birth")
	private Date date_of_birth;
	@Column(name = "gender")
	private String gender;
	@Column(name = "mobile_number")
	private String mobNo;
	@Column(name = "address")
	private String address;
	/*
	 * @Column(name = "created_at") private Timestamp createdDate;
	 * 
	 * @Column(name = "updated_at") private Timestamp updatedDate;
	 */
	@Column(name = "email")
	private String email;
	@Column(name = "current_location")
	private String current_location;
	/*
	 * @Column(name = "is_active") private boolean active;
	 */

}
