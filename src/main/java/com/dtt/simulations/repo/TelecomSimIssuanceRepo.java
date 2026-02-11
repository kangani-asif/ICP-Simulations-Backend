package com.dtt.simulations.repo;

import com.dtt.simulations.model.TelecomSimIssuance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelecomSimIssuanceRepo extends JpaRepository<TelecomSimIssuance,Integer> {

}
