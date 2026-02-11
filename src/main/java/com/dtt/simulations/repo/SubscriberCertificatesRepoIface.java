package com.dtt.simulations.repo;



import com.dtt.simulations.model.SubscriberCertificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
//import ug.daes.onboarding.model.SubscriberCertificate;

@Repository
public interface SubscriberCertificatesRepoIface extends JpaRepository<SubscriberCertificate,String>{

//	@Query(value = "select certificate_status from subscriber_certificates where subscriber_uid=?1 and certificate_type=?2 and certificate_status=?3", nativeQuery = true)
//	String getSubscriberCertificateStatus(String uid,String type,String status);
	
	
//	@Query(value = "select * from subscriber_certificates where\n" +
//			"created_date =(\n" +
//			"SELECT MAX(created_date) FROM subscriber_certificates sc \n" +
//			"WHERE subscriber_uid=?1  and certificate_type=?2 )", nativeQuery = true)
//	SubscriberCertificate getSubscriberCertificateStatus(String uid, String type);
//
//
//	@Query(value = "select max(certificate_status) as  certificate_status  from subscriber_certificate_life_cycle where subscriber_uid=?1 and certificate_type=?2 and certificate_status=?3",nativeQuery = true)
//	String getSubscriberCertificateStatusLifeHistory(String uid,String type,String status);

	@Query("SELECT COUNT(s) FROM SubscriberCertificate s " +
			"WHERE s.subscriberUid = ?1 AND s.certificateStatus = 'ACTIVE'")
	int getCountOfCert(String suid);



	
}