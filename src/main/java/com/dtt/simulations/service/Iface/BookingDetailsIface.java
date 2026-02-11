package com.dtt.simulations.service.Iface;


import com.dtt.simulations.dto.BookingDetailsDto;
import com.dtt.simulations.responseentity.ApiResponse;

public interface BookingDetailsIface {

    ApiResponse saveBookingDetails(BookingDetailsDto bookingDetailsDto);

    ApiResponse getAllBookingDetails();




}
