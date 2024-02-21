package com.lufthansa.poc.flightbookingsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity extends BaseEntity{

    @Id
    private String email;
    private String userName;
    private String contactNumber;
    private String address;

    @JsonIgnoreProperties("user")
    @OneToMany(mappedBy = "user", targetEntity = Booking.class)
    private List<Booking> bookings;
}
