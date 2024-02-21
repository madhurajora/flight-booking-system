package com.lufthansa.poc.flightbookingsystem.exception;

public class FlightNotFoundException extends RuntimeException{

    public FlightNotFoundException(String message){
        super(message);
    }
}
