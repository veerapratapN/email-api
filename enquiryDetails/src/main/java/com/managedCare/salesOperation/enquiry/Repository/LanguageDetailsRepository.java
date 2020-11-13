package com.managedCare.salesOperation.enquiry.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.managedCare.salesOperation.enquiry.Entity.LanguageDetails;

@Repository
public interface LanguageDetailsRepository  extends JpaRepository<LanguageDetails, Integer>{
	
	@Query(value = "select Language_Name from tb_language WHERE language_id = :languageId", nativeQuery = true)
	public String getNameById(int languageId);
}
