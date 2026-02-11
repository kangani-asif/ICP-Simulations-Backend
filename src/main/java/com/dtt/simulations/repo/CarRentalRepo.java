package com.dtt.simulations.repo;


import com.dtt.simulations.model.CarRental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface CarRentalRepo extends JpaRepository<CarRental,Integer> {

//    @Query(value = "select * from car_rental_simulation where id=?1 order by updated_on DESC",nativeQuery = true)
//    CarRental getCarRentalDetaildById(int id);
//
//    @Query(value = "select * from car_rental_simulation order by updated_on DESC",nativeQuery = true)
//    List<CarRental> getCarRentalDetails();
@Query("SELECT c FROM CarRental c WHERE c.id = ?1 ORDER BY c.updatedOn DESC")
CarRental getCarRentalDetaildById(int id);


    @Query("SELECT c FROM CarRental c ORDER BY c.updatedOn DESC")
    List<CarRental> getCarRentalDetails();


}
