package com.lufthansa.poc.flightbookingsystem.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Flight extends BaseEntity{

    @Id
    private long flightNumber;

    @NotBlank(message="Flight Name must not be blank")
    @Size(min=3, message="Flight Name must be at least 3 characters long")
    private String flightName;

    @NotBlank(message="Destination must not be blank")
    private String flightFrom;

    @NotBlank(message="Origin must not be blank")
    private String flightTo;

    @FutureOrPresent(message="Provide future or present date and time")
    private LocalDateTime departureTime;

    @Positive(message = "Please enter valid fare.")
    private int fare;

    @Positive
    private int totalSeats;

    @JsonIgnoreProperties("flight")
    @OneToMany(mappedBy = "flight", targetEntity = Booking.class)
    private List<Booking> bookings;

}
