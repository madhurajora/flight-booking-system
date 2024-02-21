package com.lufthansa.poc.flightbookingsystem.exception;

public class UnableToDeleteFlightException extends RuntimeException{

    public UnableToDeleteFlightException(String message){
        super(message);
    }
}
