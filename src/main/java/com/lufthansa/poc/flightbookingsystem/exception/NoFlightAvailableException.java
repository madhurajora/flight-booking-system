package com.lufthansa.poc.flightbookingsystem.exception;

public class NoFlightAvailableException extends RuntimeException{

    public NoFlightAvailableException(String message){
        super(message);
    }
}
