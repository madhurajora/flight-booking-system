package com.lufthansa.poc.flightbookingsystem.service;

import com.lufthansa.poc.flightbookingsystem.constants.FlightConstants;
import com.lufthansa.poc.flightbookingsystem.dto.FlightDto;
import com.lufthansa.poc.flightbookingsystem.entity.Flight;
import com.lufthansa.poc.flightbookingsystem.exception.FlightNotFoundException;
import com.lufthansa.poc.flightbookingsystem.exception.ResourceNotFoundException;
import com.lufthansa.poc.flightbookingsystem.exception.UnableToDeleteFlightException;
import com.lufthansa.poc.flightbookingsystem.mapper.FlightMapper;
import com.lufthansa.poc.flightbookingsystem.repository.BookingRepository;
import com.lufthansa.poc.flightbookingsystem.repository.FlightRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class FlightServiceImpl implements IFlightService{

    private FlightRepository flightRepository;
    private BookingRepository bookingRepository;

    @Override
    public long createFlight(FlightDto flightDto) {
        Flight flight = FlightMapper.mapToFlight(flightDto, new Flight());
        log.debug("Inside createFlight(FlightDto flightDto), processing request for Flight: {} "+flight);
        long flightNumber = 10000000L + new Random().nextInt(9000000);
        flight.setFlightNumber(flightNumber);
        flight.setCreatedAt(LocalDateTime.now());
        flight.setCreatedBy(FlightConstants.ANONYMOUS);
        flightRepository.save(flight);
        log.info("Inside createFlight(FlightDto flightDto), Flight created successfully {} : "+flight);
        return flightNumber;
    }

    @Override
    public boolean updateFlightDetails(FlightDto flightDto, long flightNumber) {
        boolean isUpdated = false;
        Flight flight = FlightMapper.mapToFlight(flightDto, new Flight());
        flight.setFlightNumber(flightNumber);
        flight.setUpdatedAt(LocalDateTime.now());
        flight.setUpdatedBy(FlightConstants.UPDATED_BY_ANONYMOUS);
        Flight savedFlight = flightRepository.save(flight);
        if(!ObjectUtils.isEmpty(savedFlight)) {
            isUpdated = true;
        }
        log.info("updateFlightDetails(FlightDto flightDto, long flightNumber) " +
                "Update Flight Details for Flight Number : {} "+flightNumber);
        return isUpdated;
    }

    @Override
    public boolean deleteFlight(long flightNumber) {
        Flight flight = flightRepository.findByFlightNumber(flightNumber).orElseThrow(() ->
                new ResourceNotFoundException("Flight with Flight Number " + flightNumber + " does not exists!"));
        if(!ObjectUtils.isEmpty(flight.getBookings())){
            throw new UnableToDeleteFlightException("The Flight with Flight Number "+flightNumber+
                    " cannot be deleted as booking already exists. Delete the bookings first!");
        }
        log.info("Inside deleteFlight(long flightNumber), " +
                "Deleting Flight of Flight Number : {} "+flightNumber);
        flightRepository.deleteById(flightNumber);
        return true;
    }

    @Override
    public List<FlightDto> findByFlightFromAndTo(String origin, String destination){
        List<Flight> flightList = flightRepository.findByFlightFromAndFlightTo(origin, destination).orElseThrow(() ->
                new ResourceNotFoundException("Flight from " + origin + " to " + destination + " does not exists!"));
        List<Flight> availableFlightsList = flightList.stream().filter(flight -> {
            int numberOfSeatsBooked = bookingRepository.getFlightsByFlightNumber(flight.getFlightNumber());
            int availableSeatsInAFlight = flight.getTotalSeats() - numberOfSeatsBooked;
            return availableSeatsInAFlight > 0;
        }).collect(Collectors.toList());

       if(availableFlightsList.size()<1){
           log.info("Inside findByFlightFromAndTo(String origin, String destination), " +
                   "Currently No Flight is available from : {} to: {} "+origin, destination);
           throw new FlightNotFoundException("No Flight is available at the moment from "+origin+" to "+destination);
       }
        return availableFlightsList.stream().map(flight -> FlightMapper.mapToFlightDto(flight, new FlightDto())).collect(Collectors.toList());
    }

    @Override
    public FlightDto findByFlightNumber(long flightNumber) {
        Flight flight = flightRepository.findByFlightNumber(flightNumber).orElseThrow(() ->
                new ResourceNotFoundException("Flight with Flight Number " + flightNumber + " does not exists!"));
        log.info("Inside findByFlightNumber(long flightNumber), " +
                "Flight with Flight Number : {} found."+flightNumber);
        return FlightMapper.mapToFlightDto(flight, new FlightDto());
    }
}
