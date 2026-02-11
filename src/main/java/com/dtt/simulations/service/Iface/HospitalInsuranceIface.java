package com.dtt.simulations.service.Iface;


import com.dtt.simulations.responseentity.ApiResponse;

public interface HospitalInsuranceIface {

    ApiResponse savedata(String JsonData);

    ApiResponse getHospitalInsuranceDetails();

    ApiResponse getHospitalInsuranceDetailsById(int id);
}
