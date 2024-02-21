package com.lufthansa.poc.flightbookingsystem.mapper;

import com.lufthansa.poc.flightbookingsystem.dto.FlightDto;
import com.lufthansa.poc.flightbookingsystem.entity.Flight;

public class FlightMapper {

    public static FlightDto mapToFlightDto(Flight flight, FlightDto flightDto){
        flightDto.setFlightName(flight.getFlightName());
        flightDto.setFlightFrom(flight.getFlightFrom());
        flightDto.setFlightTo(flight.getFlightTo());
        flightDto.setFare(flight.getFare());
        flightDto.setDepartureTime(flight.getDepartureTime());
        flightDto.setTotalSeats(flight.getTotalSeats());
        return flightDto;
    }

    public static Flight mapToFlight(FlightDto flightDto, Flight flight){
        flight.setFlightName(flightDto.getFlightName());
        flight.setFlightFrom(flightDto.getFlightFrom());
        flight.setFlightTo(flightDto.getFlightTo());
        flight.setFare(flightDto.getFare());
        flight.setDepartureTime(flightDto.getDepartureTime());
        flight.setTotalSeats(flightDto.getTotalSeats());
        return flight;
    }
}
