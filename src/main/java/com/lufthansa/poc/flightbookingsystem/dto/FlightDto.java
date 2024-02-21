package com.lufthansa.poc.flightbookingsystem.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FlightDto {

    @NotBlank(message="Flight Name must not be blank")
    @Size(min=3, message="Flight Name must be at least 3 characters long")
    private String flightName;

    @NotBlank(message="Destination must not be blank")
    @Size(min=2, message="Destination must be at least 2 characters long")
    private String flightFrom;

    @NotBlank(message="Origin must not be blank")
    @Size(min=2, message="Origin must be at least 2 characters long")
    private String flightTo;

    @FutureOrPresent(message="Provide future or present date and time")
    private LocalDateTime departureTime;

    @Positive(message = "Please enter valid fare.")
    private int fare;

    @Positive
    private int totalSeats;

}
