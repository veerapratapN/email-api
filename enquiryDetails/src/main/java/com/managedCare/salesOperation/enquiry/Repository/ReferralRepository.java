package com.managedCare.salesOperation.enquiry.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.managedCare.salesOperation.enquiry.Entity.Referral;

@Repository
public interface ReferralRepository extends JpaRepository<Referral, Integer> {

	@Query("FROM Referral r WHERE r.referralId >= :referralId ")
	List<Referral> returnSourceOfReferral(@Param("referralId") int referralId);

	@Query(value = "select NAME from tb_referral", nativeQuery = true)
	public List<String> findAllReferralByName();

	@Query(value = "Select Name from tb_referral where referral_id = :referralId", nativeQuery = true)
	public String getReferralById(int referralId);

}
