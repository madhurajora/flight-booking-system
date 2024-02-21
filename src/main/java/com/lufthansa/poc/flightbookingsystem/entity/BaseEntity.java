package com.lufthansa.poc.flightbookingsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter@Setter@ToString@MappedSuperclass
public class BaseEntity {

    @JsonIgnore
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @JsonIgnore
    @Column(updatable = false)
    private String createdBy;

    @JsonIgnore
    @Column(insertable = false)
    private LocalDateTime updatedAt;

    @JsonIgnore
    @Column(insertable = false)
    private String updatedBy;
}
