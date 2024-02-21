package com.lufthansa.poc.flightbookingsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponseDto {

    private String statusCode;
    private String email;
    private String message;
}
