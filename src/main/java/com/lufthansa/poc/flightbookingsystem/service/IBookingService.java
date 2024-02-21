package com.lufthansa.poc.flightbookingsystem.service;

import com.lufthansa.poc.flightbookingsystem.dto.BookingDto;
import com.lufthansa.poc.flightbookingsystem.entity.Booking;

import java.util.List;

public interface IBookingService {
    long createBooking(BookingDto bookingDto);
    List<Booking> getBookingByFlightNumber(long flightNumber);
    List<Booking> getAllBookings();

}
