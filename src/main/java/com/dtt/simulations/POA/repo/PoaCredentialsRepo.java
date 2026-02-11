package com.dtt.simulations.POA.repo;


import com.dtt.simulations.POA.model.PoaCredentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PoaCredentialsRepo extends JpaRepository<PoaCredentials, Integer> {


    @Query("SELECT p FROM PoaCredentials p WHERE p.agentEmail = :email")
    PoaCredentials fetchByEmail(String email);

    @Query("SELECT p FROM PoaCredentials p WHERE p.agentIdDocNumber = :agentIdDocNumber")
    PoaCredentials fetchByDocNumber(String agentIdDocNumber);
}

