package com.dtt.simulations.controller;



import com.dtt.simulations.responseentity.ApiResponse;
import com.dtt.simulations.service.Iface.CarRentalIface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class CarRentalController {

   @Autowired
   CarRentalIface carRentalIface;


    @PostMapping("/api/post/save/car-rental-data")
    public ApiResponse saveData(@RequestBody String carRentalJson) {
            return carRentalIface.saveCarRentalData(carRentalJson);
    }

    @GetMapping("/api/get/all/car-rental-data")
    public ApiResponse allData(){
        return carRentalIface.getAllCarRentalData();
    }

    @GetMapping("/api/get/car-rental-data/by/id/{id}")
    public ApiResponse allDatabyId(@PathVariable int id){
        return carRentalIface.getCarRentalDataById(id);
    }




}
