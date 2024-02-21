package com.lufthansa.poc.flightbookingsystem.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class BookingDto {

    private long flightNumber;

    @Email(message = "Email must be valid")
    private String email;
}
