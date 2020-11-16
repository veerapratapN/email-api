package com.managedCare.salesOperation.enquiry.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.managedCare.salesOperation.enquiry.Entity.CallReceivedBy;
@Repository
public interface EnquiryCallReceivedRepository extends JpaRepository<CallReceivedBy, Integer>{
	@Query(value = "select NAME from tb_call_received WHERE call_received_id = :callReceivedId", nativeQuery = true)
	public String getNameById(int callReceivedId);
}
