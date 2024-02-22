package com.lufthansa.poc.flightbookingsystem.controller;

import com.lufthansa.poc.flightbookingsystem.dto.FlightDto;
import com.lufthansa.poc.flightbookingsystem.dto.ResponseDto;
import com.lufthansa.poc.flightbookingsystem.entity.Flight;
import com.lufthansa.poc.flightbookingsystem.exception.ResourceNotFoundException;
import com.lufthansa.poc.flightbookingsystem.repository.BookingRepository;
import com.lufthansa.poc.flightbookingsystem.repository.FlightRepository;
import com.lufthansa.poc.flightbookingsystem.service.FlightServiceImpl;
import com.lufthansa.poc.flightbookingsystem.service.IFlightService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class FlightControllerTest {

    private FlightRepository flightRepository;
    private BookingRepository bookingRepository;
    @Autowired
    private IFlightService iFlightService;


    @Test
    void createFlight(){
        FlightRepository flightMock = mock(FlightRepository.class);
        BookingRepository bookingMock = mock(BookingRepository.class);
        FlightServiceImpl flightService = new FlightServiceImpl(flightMock, bookingMock);
        when(flightMock.save(getFlight())).thenReturn(getFlight());
        FlightController controller = new FlightController(flightService);
        ResponseEntity<ResponseDto> flight = controller.createFlight(getFlightDto());
        assertNotNull(flight);
    }

    @Test
    void findFlightFromAndTo(){
        FlightRepository flightMock = mock(FlightRepository.class);
        BookingRepository bookingMock = mock(BookingRepository.class);
        FlightServiceImpl flightService = new FlightServiceImpl(flightMock, bookingMock);
        when(flightMock.findByFlightFromAndFlightTo(getFlight().getFlightFrom(), getFlight().getFlightTo()))
                .thenReturn(Optional.of(getFlightList()));
        FlightController controller = new FlightController(flightService);
        ResponseEntity<List<FlightDto>> flightDtoList = controller.findFlightFromAndTo("Goa","Delhi");
        assertNotNull(flightDtoList);
    }

    @Test
    void findFlightFromAndToException(){
        FlightRepository flightMock = mock(FlightRepository.class);
        BookingRepository bookingMock = mock(BookingRepository.class);
        FlightServiceImpl flightService = new FlightServiceImpl(flightMock, bookingMock);
        when(flightMock.findByFlightFromAndFlightTo(getFlight().getFlightFrom(), getFlight().getFlightTo()))
                .thenReturn(Optional.of(getFlightList()));
        FlightController controller = new FlightController(flightService);
        assertThrows(ResourceNotFoundException.class, () -> {
            controller.findFlightFromAndTo("Bombay","Delhi");
        });
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

    private List<Flight> getFlightList(){
        List<Flight> flightList = new ArrayList<>();
        Flight flight1 = new Flight();
        flight1.setFlightName("Vistara");
        flight1.setFlightTo("Delhi");
        flight1.setFlightFrom("Goa");
        flight1.setTotalSeats(10);
        flight1.setFare(5000);
        flight1.setDepartureTime(LocalDateTime.now());

        Flight flight2 = new Flight();
        flight2.setFlightName("Indigo");
        flight2.setFlightTo("Delhi");
        flight2.setFlightFrom("Pune");
        flight2.setTotalSeats(10);
        flight2.setFare(4000);
        flight2.setDepartureTime(LocalDateTime.now());

        Flight flight3 = new Flight();
        flight3.setFlightName("Vistara");
        flight3.setFlightTo("Delhi");
        flight3.setFlightFrom("Pune");
        flight3.setTotalSeats(15);
        flight3.setFare(3000);
        flight3.setDepartureTime(LocalDateTime.now());

        Flight flight4 = new Flight();
        flight4.setFlightName("Indigo");
        flight4.setFlightTo("Mumbai");
        flight4.setFlightFrom("Bangalore");
        flight4.setTotalSeats(10);
        flight4.setFare(6000);
        flight4.setDepartureTime(LocalDateTime.now());

        Flight flight5 = new Flight();
        flight5.setFlightName("SpiceJet");
        flight5.setFlightTo("Delhi");
        flight5.setFlightFrom("Ahmedabad");
        flight5.setTotalSeats(10);
        flight5.setFare(5000);

        flight5.setDepartureTime(LocalDateTime.now());

        Flight flight6 = new Flight();
        flight6.setFlightName("Akasa Air");
        flight6.setFlightTo("Ahmedabad");
        flight6.setFlightFrom("Coimbatore");
        flight6.setTotalSeats(10);
        flight6.setFare(7000);
        flight6.setDepartureTime(LocalDateTime.now());

        flightList.add(flight1); flightList.add(flight2);
        flightList.add(flight3); flightList.add(flight4);
        flightList.add(flight5); flightList.add(flight6);

        return flightList;
    }

    private List<FlightDto> getFlightDtoList(){
        List<FlightDto> flightDtoList = new ArrayList<>();
        FlightDto FlightDto1 = new FlightDto();
        FlightDto1.setFlightName("Vistara");
        FlightDto1.setFlightTo("Delhi");
        FlightDto1.setFlightFrom("Goa");
        FlightDto1.setTotalSeats(10);
        FlightDto1.setFare(5000);
        FlightDto1.setDepartureTime(LocalDateTime.now());

        FlightDto FlightDto2 = new FlightDto();
        FlightDto2.setFlightName("Indigo");
        FlightDto2.setFlightTo("Delhi");
        FlightDto2.setFlightFrom("Goa");
        FlightDto2.setTotalSeats(10);
        FlightDto2.setFare(4000);
        FlightDto2.setDepartureTime(LocalDateTime.now());

        FlightDto FlightDto3 = new FlightDto();
        FlightDto3.setFlightName("Vistara");
        FlightDto3.setFlightTo("Delhi");
        FlightDto3.setFlightFrom("Pune");
        FlightDto3.setTotalSeats(15);
        FlightDto3.setFare(3000);
        FlightDto3.setDepartureTime(LocalDateTime.now());

        FlightDto FlightDto4 = new FlightDto();
        FlightDto4.setFlightName("Indigo");
        FlightDto4.setFlightTo("Mumbai");
        FlightDto4.setFlightFrom("Bangalore");
        FlightDto4.setTotalSeats(10);
        FlightDto4.setFare(6000);
        FlightDto4.setDepartureTime(LocalDateTime.now());

        FlightDto FlightDto5 = new FlightDto();
        FlightDto5.setFlightName("SpiceJet");
        FlightDto5.setFlightTo("Delhi");
        FlightDto5.setFlightFrom("Ahmedabad");
        FlightDto5.setTotalSeats(10);
        FlightDto5.setFare(5000);
        FlightDto5.setDepartureTime(LocalDateTime.now());

        FlightDto FlightDto6 = new FlightDto();
        FlightDto6.setFlightName("Akasa Air");
        FlightDto6.setFlightTo("Ahmedabad");
        FlightDto6.setFlightFrom("Coimbatore");
        FlightDto6.setTotalSeats(10);
        FlightDto6.setFare(7000);
        FlightDto6.setDepartureTime(LocalDateTime.now());

        flightDtoList.add(FlightDto1); flightDtoList.add(FlightDto2);
        flightDtoList.add(FlightDto3); flightDtoList.add(FlightDto4);
        flightDtoList.add(FlightDto5); flightDtoList.add(FlightDto6);

        return flightDtoList;
    }
}
