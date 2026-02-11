package com.dtt.simulations.POA.repo;


import com.dtt.simulations.POA.model.PoaVisitorCompleteDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PoaVisitorCompleteDetailsRepo extends JpaRepository<PoaVisitorCompleteDetails, String> {

    //    @Query(value = "select * from visitor_complete_details_nodocs where subscriber_uid=?1", nativeQuery = true)
//    PoaVisitorCompleteDetails fetchDetails(String suid);
//}
    @Query("SELECT v FROM PoaVisitorCompleteDetails v WHERE v.subscriberUid = :suid")
    PoaVisitorCompleteDetails fetchDetails(String suid);
}