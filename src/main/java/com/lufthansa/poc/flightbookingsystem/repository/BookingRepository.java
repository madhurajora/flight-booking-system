package com.lufthansa.poc.flightbookingsystem.repository;

import com.lufthansa.poc.flightbookingsystem.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Query("SELECT COUNT(*) FROM Booking b WHERE b.flight.flightNumber = ?1")
    int getFlightsByFlightNumber(long flightNumber);

    Optional<List<Booking>> findByUserEmail(String email);

    List<Booking> findByFlightFlightNumber(long flightNumber);

}
