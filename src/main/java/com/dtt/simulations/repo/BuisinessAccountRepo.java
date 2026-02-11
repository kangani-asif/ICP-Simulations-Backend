package com.dtt.simulations.repo;


import com.dtt.simulations.model.BusinessAccountModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuisinessAccountRepo extends JpaRepository<BusinessAccountModel,Integer> {


//    @Query(value = "select * from bank where id =?1 order by created_on DESC Limit 1",nativeQuery = true)
//    BusinessAccountModel getbyId(int id);
//
//
//    @Query(value = "select * from bank where passport_number = ?1 order by id DESC Limit 1",nativeQuery =  true)
//    BusinessAccountModel getByPassportId(String passportNumber);
//
//    @Query(value="select * from bank order by id desc",nativeQuery = true)
//    List<BusinessAccountModel> getAllData();


    @Query("SELECT b FROM BusinessAccountModel b WHERE b.id = ?1 ORDER BY b.createdOn DESC")
    BusinessAccountModel getbyId(int id);


    @Query("SELECT b FROM BusinessAccountModel b WHERE b.passportNumber = ?1 ORDER BY b.id DESC")
    BusinessAccountModel getByPassportId(String passportNumber);


    @Query("SELECT b FROM BusinessAccountModel b ORDER BY b.id DESC")
    List<BusinessAccountModel> getAllData();
}
