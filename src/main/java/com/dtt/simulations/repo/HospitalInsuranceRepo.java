package com.dtt.simulations.repo;


import com.dtt.simulations.model.HospitalInsurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HospitalInsuranceRepo extends JpaRepository<HospitalInsurance, Integer> {

//    @Query(value = "SELECT count(*) FROM hospital_insurance_policy where policy_number = ?1 ", nativeQuery = true)
//    int countNoPolicyNumber(String policyNumber);
//
//    @Query(value = "select * from hospital_insurance_policy order by updated_on DESC ;",nativeQuery = true)
//    List<HospitalInsurance> getHospitalInsuranceDetails();
//
//    @Query(value = "select * from hospital_insurance_policy where id=?1",nativeQuery = true)
//    HospitalInsurance getHospitalInsuranceDetailsById(int id);


    @Query("SELECT COUNT(h) FROM HospitalInsurance h WHERE h.policy_number = ?1")
    int countNoPolicyNumber(String policyNumber);


    @Query("SELECT h FROM HospitalInsurance h ORDER BY h.updatedOn DESC")
    List<HospitalInsurance> getHospitalInsuranceDetails();


    @Query("SELECT h FROM HospitalInsurance h WHERE h.id = ?1")
    HospitalInsurance getHospitalInsuranceDetailsById(int id);
}
