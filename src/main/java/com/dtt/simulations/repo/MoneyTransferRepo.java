package com.dtt.simulations.repo;

import com.dtt.simulations.model.MoneyTransfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MoneyTransferRepo extends JpaRepository<MoneyTransfer, Integer> {


//    @Query(value = "select * from use_of_poa where id=?1",nativeQuery = true)
//    MoneyTransfer findById(int id);

    @Query("SELECT m FROM MoneyTransfer m WHERE m.id = ?1")
    MoneyTransfer findById(int id);



}
