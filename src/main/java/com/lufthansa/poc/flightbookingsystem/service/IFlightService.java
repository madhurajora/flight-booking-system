package com.lufthansa.poc.flightbookingsystem.service;

import com.lufthansa.poc.flightbookingsystem.dto.FlightDto;
import com.lufthansa.poc.flightbookingsystem.entity.Flight;

import java.util.List;

public interface IFlightService {
    long createFlight(FlightDto flightDto);

    boolean updateFlightDetails(FlightDto flightDto, long flightNumber);
    boolean deleteFlight(long flightNumber);


    List<FlightDto> findByFlightFromAndTo(String origin, String destination);
    FlightDto findByFlightNumber(long flightNumber);


}
