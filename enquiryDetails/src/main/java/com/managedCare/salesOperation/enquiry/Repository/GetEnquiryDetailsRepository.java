package com.managedCare.salesOperation.enquiry.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.managedCare.salesOperation.enquiry.Entity.GetEnquiry;

@Repository
public interface GetEnquiryDetailsRepository extends JpaRepository<GetEnquiry, Integer>{
	@Query(value = "Select enquiry_id,enquired_on,activities,adl,assigned_to,complaint,enquiry_status,patient_height,lead_type_id,medical_history,caller_id,received_id,seen_doctor,source_of_referreal,patient_weight from tb_enquiry_details  where enquiry_id= :id",nativeQuery=true)
	GetEnquiry findEnquiryById(int id);
	
	GetEnquiry findByEnquiryId(int id);
}
