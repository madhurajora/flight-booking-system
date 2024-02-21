package com.lufthansa.poc.flightbookingsystem.repository;

import com.lufthansa.poc.flightbookingsystem.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    Optional<List<Flight>> findByFlightFromAndFlightTo(String origin, String destination);
    Optional<Flight> findByFlightNumber(long flightNumber);

}
