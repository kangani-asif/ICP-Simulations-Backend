package com.dtt.simulations.POA.repo;


import com.dtt.simulations.POA.model.PowerOfAttorney;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PoaPowerOfAttorneyRepo extends JpaRepository<PowerOfAttorney, Integer> {
//    @Query(value = " SELECT * FROM power_of_attorney WHERE principle_id_doc_number = ?1 ORDER BY created_on DESC LIMIT 1 ", nativeQuery = true)
//    PowerOfAttorney fetchApprovedPoaRequests(String idDocNumber);


    @Query("SELECT p FROM PowerOfAttorney p " +
            "WHERE p.principleIdDocNumber = :idDocNumber " +
            "AND p.status = 'APPROVED'")
    PowerOfAttorney fetchApprovedPoaRequests(@Param("idDocNumber") String idDocNumber);


}
