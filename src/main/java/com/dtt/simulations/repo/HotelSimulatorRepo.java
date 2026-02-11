package com.dtt.simulations.repo;


import com.dtt.simulations.model.HotelSimulator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HotelSimulatorRepo extends JpaRepository<HotelSimulator,Integer> {

//    @Query(value = "SELECT * FROM hospitality order by creation_date DESC", nativeQuery = true)
//    public List<HotelSimulator> allBookings();

    @Query("SELECT h FROM HotelSimulator h ORDER BY h.creationDate DESC")
    List<HotelSimulator> allBookings();






}
