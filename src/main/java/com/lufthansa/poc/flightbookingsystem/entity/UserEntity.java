package com.lufthansa.poc.flightbookingsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
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

    @JsonIgnore
    private String pwd;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", referencedColumnName = "roleId")
    private Roles role;

    @JsonIgnoreProperties("user")
    @OneToMany(mappedBy = "user", targetEntity = Booking.class)
    private List<Booking> bookings;
}
