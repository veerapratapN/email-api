package com.managedCare.salesOperation.enquiry.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.managedCare.salesOperation.enquiry.Entity.QuoteAttachement;


public interface QuoteRepository  extends JpaRepository<QuoteAttachement, Integer> {
	

}
