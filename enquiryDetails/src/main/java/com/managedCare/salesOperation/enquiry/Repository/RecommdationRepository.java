package com.managedCare.salesOperation.enquiry.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.managedCare.salesOperation.enquiry.Entity.RecommdationDetails;

@Repository
public interface RecommdationRepository  extends JpaRepository<RecommdationDetails, Integer>{
	
	@Query(value = "select Recommdation_Name from tb_recommdation WHERE recommdation_id = :recommdationId", nativeQuery = true)
	public String getNameById(int recommdationId);
	@Query(value = "select Type from tb_recommdation WHERE recommdation_id = :recommdationId", nativeQuery = true)
	public String getTypeById(int recommdationId);
	

}
