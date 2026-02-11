package com.dtt.simulations.controller;



import com.dtt.simulations.dto.BookingDetailsDto;
import com.dtt.simulations.responseentity.ApiResponse;
import com.dtt.simulations.service.Iface.BookingDetailsIface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
public class HotelSimulatorController {

    @Autowired

    BookingDetailsIface bookingDetailsIface;


    @PostMapping("/api/post/save/booking-details")
    public ApiResponse saveBookingDetails(@RequestBody BookingDetailsDto bookingDetailsDto) {

        return bookingDetailsIface.saveBookingDetails(bookingDetailsDto);
    }


    @GetMapping("/api/get/all/booking-details")
    public ApiResponse getAllBookingDetails() {
        return bookingDetailsIface.getAllBookingDetails();
    }





}






