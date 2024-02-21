package com.lufthansa.poc.flightbookingsystem.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UserDto {

    @Email(message = "Email must be valid")
    @NotEmpty(message = "Email must not be empty")
    private String email;

    @NotBlank(message="Name must not be blank")
    @Size(min=3, message="Name must be at least 3 characters long")
    private String name;

    @NotEmpty(message = "Contact number must not be null or empty")
    @Pattern(regexp="(^$|[0-9]{10})",message="Contact number must be 10 digits long")
    private String contactNumber;

    @NotBlank(message="Address must not be blank")
    @Size(min=3, message="Address must be at least 3 characters long")
    private String address;
}
