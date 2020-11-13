package com.managedCare.salesOperation.enquiry.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.managedCare.salesOperation.enquiry.Entity.EnquireDetails;

@Repository
public interface AddEnquiryRepository  extends JpaRepository<EnquireDetails, Integer>{

}
