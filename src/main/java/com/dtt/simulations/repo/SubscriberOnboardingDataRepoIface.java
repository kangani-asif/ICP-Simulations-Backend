/**
 *
 */
package com.dtt.simulations.repo;

import com.dtt.simulations.model.SubscriberOnboardingData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Raxit Dubey
 *
 */
@Repository
public interface SubscriberOnboardingDataRepoIface extends JpaRepository<SubscriberOnboardingData, Integer> {

    SubscriberOnboardingData findBysubscriberUid(String suid);

//	@Query(value = "select * from subscriber_onboarding_data where subscriber_uid = ?1 ORDER BY created_date DESC limit 1",nativeQuery = true)
//	List<SubscriberOnboardingData> getBySubUid(String uid);
//
//	@Query(value = "select * from subscriber_onboarding_data where subscriber_uid = ?1"+
//			" ORDER BY created_date DESC LIMIT 1",nativeQuery = true)
//	SubscriberOnboardingData findLatestSubscriber(String suid);
//
//
//	@Query(value = "SELECT COUNT(optional_data1),onboarding_method='NIN' from subscriber_onboarding_data sod where optional_data1 =?", nativeQuery = true)
//	int getOptionalData1(String optionalData1);
//
//	@Query(value = "SELECT DISTINCT subscriber_uid from subscriber_onboarding_data sod where optional_data1 =? ", nativeQuery = true)
//	String getOptionalData1Subscriber(String optionalData1);
//
//	@Query(value = "SELECT * from subscriber_onboarding_data sod where id_doc_number=? ", nativeQuery = true)
//	List<SubscriberOnboardingData> findSubscriberByDocId(String documentNumber);
//
//	@Query(value = "select * from subscriber_onboarding_data",nativeQuery = true)
//	List<SubscriberOnboardingData> getAllSelfies();


    // 1. Get latest subscriber onboarding data for a UID (JPQL version)
    @Query("SELECT s FROM SubscriberOnboardingData s WHERE s.subscriberUid = :uid ORDER BY s.createdDate DESC")
    List<SubscriberOnboardingData> getBySubUid(@Param("uid") String uid);

    // 2. Get latest single record (return single entity)
    @Query("SELECT s FROM SubscriberOnboardingData s WHERE s.subscriberUid = :suid ORDER BY s.createdDate DESC")
    SubscriberOnboardingData findLatestSubscriber(@Param("suid") String suid);

    // 3. Count records with optional_data1 = ? (JPQL)
    @Query("SELECT COUNT(s) FROM SubscriberOnboardingData s WHERE s.optionalData1 = :optionalData1")
    int getOptionalData1(@Param("optionalData1") String optionalData1);

    // 4. Get distinct subscriber UID for a given optional_data1
    @Query("SELECT DISTINCT s.subscriberUid FROM SubscriberOnboardingData s WHERE s.optionalData1 = :optionalData1")
    String getOptionalData1Subscriber(@Param("optionalData1") String optionalData1);

    // 5. Find subscriber by ID document number
    @Query("SELECT s FROM SubscriberOnboardingData s WHERE s.idDocNumber = :documentNumber")
    List<SubscriberOnboardingData> findSubscriberByDocId(@Param("documentNumber") String documentNumber);

    // 6. Get all subscriber onboarding data
    @Query("SELECT s FROM SubscriberOnboardingData s")
    List<SubscriberOnboardingData> getAllSelfies();

    Optional<SubscriberOnboardingData>
    findTopByIdDocNumberIgnoreCaseOrderByCreatedDateDesc(String idDocNumber);



//    @Query("SELECT s FROM SubscriberOnboardingData s " +
//            "WHERE s.idDocNumber = :idDocNumber " +
//            "ORDER BY s.createdDate DESC")
//    Optional<SubscriberOnboardingData> findSubscriberByDocIdLatestRecord(@Param("idDocNumber") String idDocNumber);
//
//

//	@Query(value = "SELECT * from subscriber_onboarding_data sod where id_doc_number=? ORDER BY created_date DESC limit 1", nativeQuery = true)
//	SubscriberOnboardingData findSubscriberByDocIdLatestRecord(String documentNumber);
}
