package com.managedCare.salesOperation.enquiry.Repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.managedCare.salesOperation.enquiry.Entity.LeadType;

@Repository
public interface EnquiryLeadTypeRepository extends JpaRepository<LeadType, Integer> {
	
	@Query(" FROM LeadType t WHERE t.id >=  :id ")
	LeadType returnLeadType(@Param("id") int id);

}
