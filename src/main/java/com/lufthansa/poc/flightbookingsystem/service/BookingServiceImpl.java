package com.lufthansa.poc.flightbookingsystem.service;

import com.lufthansa.poc.flightbookingsystem.constants.FlightConstants;
import com.lufthansa.poc.flightbookingsystem.dto.BookingDto;
import com.lufthansa.poc.flightbookingsystem.entity.Booking;
import com.lufthansa.poc.flightbookingsystem.entity.Flight;
import com.lufthansa.poc.flightbookingsystem.exception.FlightNotFoundException;
import com.lufthansa.poc.flightbookingsystem.exception.NoSeatAvailableException;
import com.lufthansa.poc.flightbookingsystem.exception.ResourceNotFoundException;
import com.lufthansa.poc.flightbookingsystem.exception.UnauthorizedUserNotAllowedToUpdateException;
import com.lufthansa.poc.flightbookingsystem.mapper.BookingMapper;
import com.lufthansa.poc.flightbookingsystem.repository.BookingRepository;
import com.lufthansa.poc.flightbookingsystem.repository.FlightRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class BookingServiceImpl implements IBookingService{

    private FlightRepository flightRepository;
    private BookingRepository bookingRepository;

    @Override
    public long createBooking(BookingDto bookingDto) {
        String currentLoggedInUser = SecurityContextHolder.getContext().getAuthentication().getName();
        if(!bookingDto.getEmail().equals(currentLoggedInUser)){
            throw new UnauthorizedUserNotAllowedToUpdateException("User not allowed to book a flight");
        }
        log.debug("Inside createBooking(BookingDto bookingDto), Start creating booking for user {} : "+bookingDto.getEmail());
        Booking booking = BookingMapper.mapToBooking(bookingDto, new Booking());

        Flight flight = flightRepository.findByFlightNumber(booking.getFlight().getFlightNumber()).orElseThrow(() ->
                new ResourceNotFoundException("Flight with Flight Number " + booking.getFlight().getFlightNumber() + " does not exists!"));
        int numberOfFlightsBooked = bookingRepository.getFlightsByFlightNumber(flight.getFlightNumber());
        int availableFlights = flight.getTotalSeats() - numberOfFlightsBooked;
        if(availableFlights==0){
            throw new NoSeatAvailableException("No Seats Available for flight number: "+flight.getFlightNumber());
        }
        long bookingNumber = 10000000L + new Random().nextInt(9000000);
        booking.setBookingNumber(bookingNumber);
        booking.setCreatedAt(LocalDateTime.now());
        booking.setCreatedBy(FlightConstants.ANONYMOUS);
        bookingRepository.save(booking);
        log.info("Booking created successfully for flightNumber {} : "+flight.getFlightNumber());
        return bookingNumber;
    }

    @Override
    public List<Booking> getBookingByFlightNumber(long flightNumber) {
        List<Booking> listOfBookingsByFlightNumber = bookingRepository.findByFlightFlightNumber(flightNumber);
        if(listOfBookingsByFlightNumber.size()>0){
            log.info("Inside getBookingByFlightNumber(), fetched the booking details for Flight Number : {} "+flightNumber);
            return listOfBookingsByFlightNumber;
        }
        throw new FlightNotFoundException("Flight with number "+flightNumber+" does not exists!");
    }

    @Override
    public List<Booking> getAllBookings() {
        log.info("Inside getAllBookings(), fetched the all booking details");
        return bookingRepository.findAll();
    }

}
