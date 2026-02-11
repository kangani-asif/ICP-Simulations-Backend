package com.dtt.simulations.service.Iface;


import com.dtt.simulations.responseentity.ApiResponse;

public interface CarRentalIface {

     ApiResponse saveCarRentalData(String carRentalJson);

     ApiResponse getAllCarRentalData();

     ApiResponse getCarRentalDataById(int id);
}
