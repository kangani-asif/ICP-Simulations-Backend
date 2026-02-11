package com.dtt.simulations.POA.repo;

import com.dtt.simulations.POA.model.PoaOrgContactsEmail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PoaOrgContactEmailRepo extends JpaRepository<PoaOrgContactsEmail,Integer> {
//    @Query(value = "select * from org_subscriber_email where organization_uid=?1 and  is_org_signatory=1 and is_eseal_signatory=1 and ugpass_user_link_approved=1",nativeQuery = true)
//    List<PoaOrgContactsEmail> fetchAllEmployees(String orgId);

    @Query("SELECT o FROM PoaOrgContactsEmail o " +
            "WHERE o.organizationUid = ?1 " +
            "AND o.signatory = true " +
            "AND o.eSealSignatory = true " +
            "AND o.ugpassUserLinkApproved = true")
    List<PoaOrgContactsEmail> fetchAllEmployees(String orgId);

}
