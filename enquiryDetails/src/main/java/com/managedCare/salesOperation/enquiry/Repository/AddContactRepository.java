package com.managedCare.salesOperation.enquiry.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.managedCare.salesOperation.enquiry.Entity.AddEnquiryContact;

public interface AddContactRepository extends JpaRepository<AddEnquiryContact, Integer> {

}
