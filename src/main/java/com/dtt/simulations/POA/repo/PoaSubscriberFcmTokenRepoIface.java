package com.dtt.simulations.POA.repo;




import com.dtt.simulations.POA.model.PoaSubscriberFcmToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PoaSubscriberFcmTokenRepoIface extends JpaRepository<PoaSubscriberFcmToken, Integer>{

	PoaSubscriberFcmToken findBysubscriberUid(String suid);
	
}
