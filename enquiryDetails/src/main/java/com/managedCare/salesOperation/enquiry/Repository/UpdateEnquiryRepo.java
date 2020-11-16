package com.managedCare.salesOperation.enquiry.Repository;

import java.sql.Timestamp;
import java.util.List;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.managedCare.salesOperation.enquiry.Entity.GetEnquiry;


@Repository
@Transactional
public interface UpdateEnquiryRepo extends JpaRepository<GetEnquiry, Integer>{
	@Query(value = "Select enquiry_id,enquired_on,activities,adl,assigned_to,complaint,enquiry_status,patient_height,lead_type_id,medical_history,caller_id,received_id,seen_doctor,source_of_referreal,patient_weight from tb_enquiry_details  where enquiry_id= :id", nativeQuery = true)
	GetEnquiry findEnquiryById(int id);

	@Modifying

	/*
	 * @Query(value =
	 * "Update tb_enquiry_details  set enquired_on = :enquired_on,lead_type_id=:lead_type_id,received_id=:received_id,assigned_to = :assigned_to,source_of_referreal=:source_of_referreal,complaint=:complain,medical_history=:medicalHistory,seen_doctor=:seenDoctor,patient_height=:height,patient_weight=:weight,enquiry_status=:enquiry_status,adl=:adl where enquiry_id= :id"
	 * , nativeQuery = true) int updateEnquiry(Timestamp enquired_on, int
	 * lead_type_id, int received_id, int assigned_to, int source_of_referreal,
	 * String complain, String medicalHistory, boolean seenDoctor, String height,
	 * String weight, String enquiry_status, String adl, int id);
	 */
	
	
	@Query(value = "Update tb_enquiry_details  set enquired_on = :enquired_on,activities=:activities,lead_type_id=:lead_type_id,received_id=:received_id,assigned_to = :assigned_to,source_of_referreal=:source_of_referreal,complaint=:complain,medical_history=:medicalHistory,seen_doctor=:seenDoctor,patient_height=:height,patient_weight=:weight,enquiry_status=:enquiry_status,adl=:adl where enquiry_id= :id", nativeQuery = true)
	int updateEnquiry(Timestamp enquired_on, int lead_type_id, int received_id, int assigned_to,
			int source_of_referreal, String complain, String medicalHistory, boolean seenDoctor, String height,
			String weight, String enquiry_status, String adl,List activities,  int id);

	// caller_id=:caller_id;
	// AddEnquiryContact contact_information,
	// String activities,
	// ,activities=:activities
	//List activities,
}
