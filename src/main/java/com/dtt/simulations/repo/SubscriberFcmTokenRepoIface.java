package com.dtt.simulations.repo;

import com.dtt.simulations.model.SubscriberFCMToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SubscriberFcmTokenRepoIface extends JpaRepository<SubscriberFCMToken, Integer>{

	SubscriberFCMToken findBysuid(String suid);

	
}
