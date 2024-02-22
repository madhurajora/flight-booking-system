package com.lufthansa.poc.flightbookingsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Roles {

    @Id
    private int roleId;
    private String roleName;

    @JsonIgnoreProperties("role")
    @OneToMany(mappedBy = "role", targetEntity = UserEntity.class)
    private List<UserEntity> users;

    public Roles(int roleId){
        this.roleId = roleId;
    }
}
