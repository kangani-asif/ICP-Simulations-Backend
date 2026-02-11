/**
 * 
 */
package com.dtt.simulations.repo;

import com.dtt.simulations.model.SubscriberStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



/**
 * @author Raxit Dubey
 *
 */
@Repository
public interface SubscriberStatusRepoIface extends JpaRepository<SubscriberStatus, Integer>{

	SubscriberStatus findBysubscriberUid(String suid);
	
}
