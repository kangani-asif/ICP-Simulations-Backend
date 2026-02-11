package com.dtt.simulations.POA.repo;



import com.dtt.simulations.POA.model.PoaTemporary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PoaTemporaryPoaRepo extends JpaRepository<PoaTemporary,Integer> {

//    @Query(value = "select * from temporary_poa where id=?1",nativeQuery = true)
//    PoaTemporary fetchById(int id);

//    @Query(value = "select * from temporary_poa where status!=?1",nativeQuery = true)
//    List<PoaTemporary> fetchNotCompletedDocs(String status);

//    @Query("SELECT t FROM PoaTemporary t WHERE t.id = ?1")
//    PoaTemporary fetchById(int id);
//
//
//    @Query("SELECT t FROM PoaTemporary t WHERE t.status <> :status")
//    List<PoaTemporary> fetchNotCompletedDocs(String status);

    @Query("SELECT t FROM PoaTemporary t WHERE t.id = :id")
    PoaTemporary fetchById(@Param("id") Integer id);

    @Query("SELECT t FROM PoaTemporary t WHERE t.status <> :status")
    List<PoaTemporary> fetchNotCompletedDocs(@Param("status") String status);



}
