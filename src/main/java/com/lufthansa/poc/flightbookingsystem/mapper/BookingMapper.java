package com.lufthansa.poc.flightbookingsystem.mapper;

import com.lufthansa.poc.flightbookingsystem.dto.BookingDto;
import com.lufthansa.poc.flightbookingsystem.entity.Booking;
import com.lufthansa.poc.flightbookingsystem.entity.Flight;
import com.lufthansa.poc.flightbookingsystem.entity.UserEntity;

public class BookingMapper {

    public static BookingDto mapToBookingDto(Booking booking, BookingDto bookingDto){
        bookingDto.setFlightNumber(booking.getFlight().getFlightNumber());
        bookingDto.setEmail(booking.getUser().getEmail());
        return bookingDto;
    }

    public static Booking mapToBooking(BookingDto bookingDto, Booking booking){
        Flight flight = new Flight();
        flight.setFlightNumber(bookingDto.getFlightNumber());
        booking.setFlight(flight);

        UserEntity user = new UserEntity();
        user.setEmail(bookingDto.getEmail());
        booking.setUser(user);
        return booking;
    }
}
