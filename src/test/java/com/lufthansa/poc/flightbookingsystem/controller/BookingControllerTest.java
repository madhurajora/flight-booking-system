package com.lufthansa.poc.flightbookingsystem.controller;

import com.lufthansa.poc.flightbookingsystem.dto.BookingDto;
import com.lufthansa.poc.flightbookingsystem.dto.FlightDto;
import com.lufthansa.poc.flightbookingsystem.dto.ResponseDto;
import com.lufthansa.poc.flightbookingsystem.entity.Booking;
import com.lufthansa.poc.flightbookingsystem.entity.Flight;
import com.lufthansa.poc.flightbookingsystem.entity.UserEntity;
import com.lufthansa.poc.flightbookingsystem.exception.ResourceNotFoundException;
import com.lufthansa.poc.flightbookingsystem.exception.UnauthorizedUserNotAllowedToUpdateException;
import com.lufthansa.poc.flightbookingsystem.repository.BookingRepository;
import com.lufthansa.poc.flightbookingsystem.repository.FlightRepository;
import com.lufthansa.poc.flightbookingsystem.service.BookingServiceImpl;
import com.lufthansa.poc.flightbookingsystem.service.FlightServiceImpl;
import com.lufthansa.poc.flightbookingsystem.service.IBookingService;
import com.lufthansa.poc.flightbookingsystem.service.IFlightService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BookingControllerTest {

    private FlightRepository flightRepository;
    private BookingRepository bookingRepository;
    @Autowired
    private IBookingService iBookingService;

    @Test
    void createBooking(){
        FlightRepository flightMock = mock(FlightRepository.class);
        BookingRepository bookingMock = mock(BookingRepository.class);
        BookingServiceImpl bookingService = new BookingServiceImpl(flightMock, bookingMock);

        Authentication authentication = Mockito.mock(Authentication.class);
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        Mockito.when(SecurityContextHolder.getContext().getAuthentication().getName()).thenReturn("akhil@gmail.com");

        when(flightMock.findByFlightNumber(getBookingDto().getFlightNumber())).thenReturn(Optional.of(getFlight()));
        when(bookingMock.findByFlightFlightNumber(getBookingDto().getFlightNumber())).thenReturn(getBookingListWithSameFlightNumber());
        when(bookingMock.save(getBooking())).thenReturn(getBooking());

        BookingController controller = new BookingController(bookingService);
        ResponseEntity<ResponseDto> booking = controller.createBooking(getBookingDto());
        assertNotNull(booking);
    }

    @Test
    void createBookingWhenUserNotAuthorized(){
        FlightRepository flightMock = mock(FlightRepository.class);
        BookingRepository bookingMock = mock(BookingRepository.class);
        BookingServiceImpl bookingService = new BookingServiceImpl(flightMock, bookingMock);

        Authentication authentication = Mockito.mock(Authentication.class);
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        Mockito.when(SecurityContextHolder.getContext().getAuthentication().getName()).thenReturn("khushi@gmail.com");

        when(flightMock.findByFlightNumber(getBookingDto().getFlightNumber())).thenReturn(Optional.of(getFlight()));
        when(bookingMock.findByFlightFlightNumber(getBookingDto().getFlightNumber())).thenReturn(getBookingListWithSameFlightNumber());
        when(bookingMock.save(getBooking())).thenReturn(getBooking());

        BookingController controller = new BookingController(bookingService);

        assertThrows(UnauthorizedUserNotAllowedToUpdateException.class, () -> {
            controller.createBooking(getBookingDto());
        });
    }

    private BookingDto getBookingDto(){
        BookingDto bookingDto = new BookingDto();
        bookingDto.setEmail("akhil@gmail.com");
        bookingDto.setFlightNumber(1234567);
        return bookingDto;
    }

    private Booking getBooking(){
        Booking booking = new Booking();
        UserEntity user = new UserEntity();
        user.setEmail("akhil@gmail.com");
        booking.setUser(user);

        Flight flight = new Flight();
        flight.setFlightNumber(1234567);
        booking.setFlight(flight);

        return booking;
    }

    private FlightDto getFlightDto(){
        FlightDto flightDto = new FlightDto();
        flightDto.setFlightName("Vistara");
        flightDto.setFlightTo("Delhi");
        flightDto.setFlightFrom("Goa");
        flightDto.setTotalSeats(10);
        flightDto.setFare(5000);
        flightDto.setDepartureTime(LocalDateTime.now());
        return flightDto;
    }
    private Flight getFlight(){
        Flight flight = new Flight();
        flight.setFlightName("Vistara");
        flight.setFlightTo("Delhi");
        flight.setFlightFrom("Goa");
        flight.setTotalSeats(10);
        flight.setFare(5000);
        flight.setDepartureTime(LocalDateTime.now());
        return flight;
    }

    private List<Booking> getBookingListWithSameFlightNumber(){
        List<Booking> bookings = new ArrayList<>();
        Booking booking1 = new Booking();
        UserEntity user1 = new UserEntity();
        user1.setEmail("akhil@gmail.com");
        booking1.setUser(user1);
        Flight flight1 = new Flight();
        flight1.setFlightNumber(1234567);
        booking1.setFlight(flight1);

        Booking booking2 = new Booking();
        UserEntity user2 = new UserEntity();
        user1.setEmail("khushi@gmail.com");
        booking1.setUser(user2);
        Flight flight2 = new Flight();
        flight2.setFlightNumber(1234567);
        booking2.setFlight(flight1);

        bookings.add(booking1);
        bookings.add(booking2);

        return bookings;
    }
}
