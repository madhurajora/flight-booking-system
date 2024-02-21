package com.lufthansa.poc.flightbookingsystem.controller;

import com.lufthansa.poc.flightbookingsystem.dto.BookingDto;
import com.lufthansa.poc.flightbookingsystem.dto.ResponseDto;
import com.lufthansa.poc.flightbookingsystem.entity.Booking;
import com.lufthansa.poc.flightbookingsystem.service.IBookingService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@Validated
@RequestMapping("/api")
public class BookingController {

    private IBookingService iBookingService;

    @PostMapping("/booking")
    public ResponseEntity<ResponseDto> createBooking(@Valid @RequestBody BookingDto bookingDto){
        log.debug("Inside createBooking(), Request for booking is received: {} "+bookingDto);
        long bookingId = iBookingService.createBooking(bookingDto);
        String message = "Your flight is booked.";
        ResponseDto response = new ResponseDto("201", bookingId,message);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/flight/booking")
    public ResponseEntity<List<Booking>> getBookingByFlightNumber(@RequestParam(name="flightNumber") long flightNumber){
        log.debug("Inside getBookingByFlightNumber(), Request for fetching Flight with Flight Number : {} "+flightNumber);
        List<Booking> bookingByFlightNumber = iBookingService.getBookingByFlightNumber(flightNumber);
        return new ResponseEntity<>(bookingByFlightNumber, HttpStatus.ACCEPTED);
    }

    @GetMapping("/booking")
    public ResponseEntity<List<Booking>> getAllBookings(){
        log.debug("Inside getAllBookings(), Request for fetching all bookings");
        List<Booking> allBookings = iBookingService.getAllBookings();
        return new ResponseEntity<>(allBookings, HttpStatus.ACCEPTED);
    }


}
