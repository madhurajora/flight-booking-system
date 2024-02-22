package com.lufthansa.poc.flightbookingsystem.exception;

public class BadCredentialsException extends RuntimeException{

    public BadCredentialsException(String message){
        super(message);
    }
}
