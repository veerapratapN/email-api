package com.managedCare.salesOperation.enquiry.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.managedCare.salesOperation.enquiry.Entity.CareCoordinator;
import com.managedCare.salesOperation.enquiry.Entity.Referral;

public interface AssignedByRepository extends JpaRepository<CareCoordinator,Long >{
	

	@Query("FROM CareCoordinator c WHERE c.carecoordinator_id >= :carecoordinator_id ")
	CareCoordinator returnAssignedBy(@Param("carecoordinator_id") long carecoordinator_id);

}
