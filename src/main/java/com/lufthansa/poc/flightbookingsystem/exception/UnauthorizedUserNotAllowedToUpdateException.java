package com.lufthansa.poc.flightbookingsystem.exception;

public class UnauthorizedUserNotAllowedToUpdateException extends RuntimeException{

    public UnauthorizedUserNotAllowedToUpdateException(String message){
        super(message);
    }
}
