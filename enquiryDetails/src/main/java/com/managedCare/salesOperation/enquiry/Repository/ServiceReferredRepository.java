package com.managedCare.salesOperation.enquiry.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.managedCare.salesOperation.enquiry.Entity.ServicesReferred;

@Repository
public interface ServiceReferredRepository extends JpaRepository<ServicesReferred, Integer>{

	@Query(value = "select Services_Referred_Name from tb_services_referred WHERE services_referred_id = :serviceReferredId", nativeQuery = true)
	public String getNameById(int serviceReferredId);
}
