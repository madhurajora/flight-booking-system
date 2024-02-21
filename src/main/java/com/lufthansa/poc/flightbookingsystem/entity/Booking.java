package com.lufthansa.poc.flightbookingsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@Getter
@Setter@ToString
@NoArgsConstructor@AllArgsConstructor
public class Booking extends BaseEntity{

    @Id
    private long bookingNumber;

    @JsonIgnoreProperties("bookings")
    @ManyToOne
    @JoinColumn(name = "flight_number", referencedColumnName = "flightNumber")
    private Flight flight;

    @JsonIgnoreProperties("bookings")
    @ManyToOne
    @JoinColumn(name = "email", referencedColumnName = "email")
    private UserEntity user;
}
