package com.dtt.simulations.POA.repo;

import com.dtt.simulations.POA.model.PoaSubscriber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PoaSubscriberRepo extends JpaRepository<PoaSubscriber,Integer> {
//    @Query(value = "select * from subscribers where email_id=?1",nativeQuery = true)
//    PoaSubscriber fetchSubscriberByEmailId(String email);
//
//    @Query(value = "select * from subscribers where id_doc_number=?1",nativeQuery = true)
//    PoaSubscriber fetchSubscriberByIDDocNumber(String idDocNumber);


    @Query("SELECT s FROM PoaSubscriber s WHERE s.emailId = ?1")
    PoaSubscriber fetchSubscriberByEmailId(String email);

    @Query("SELECT s FROM PoaSubscriber s WHERE s.idDocNumber = ?1")
    PoaSubscriber fetchSubscriberByIDDocNumber(String idDocNumber);
}



