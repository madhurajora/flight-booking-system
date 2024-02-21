package com.lufthansa.poc.flightbookingsystem.exception;

import com.lufthansa.poc.flightbookingsystem.dto.ErrorResponseDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoSeatAvailableException.class)
    public ResponseEntity<ErrorResponseDto> handleNoSeatAvailableException(NoSeatAvailableException exception, WebRequest webRequest){
        ErrorResponseDto errorResponse = new ErrorResponseDto();
        errorResponse.setApiPath(webRequest.getDescription(false));
        errorResponse.setErrorMessage(exception.getMessage());
        errorResponse.setErrorCode(HttpStatus.OK);
        errorResponse.setErrorTime(LocalDateTime.now());
        return new ResponseEntity<>(errorResponse, HttpStatusCode.valueOf(200));
    }

    @ExceptionHandler(FlightNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleFlightNotFoundException(FlightNotFoundException exception, WebRequest webRequest){
        ErrorResponseDto errorResponse = new ErrorResponseDto();
        errorResponse.setApiPath(webRequest.getDescription(false));
        errorResponse.setErrorMessage(exception.getMessage());
        errorResponse.setErrorCode(HttpStatus.BAD_REQUEST);
        errorResponse.setErrorTime(LocalDateTime.now());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest webRequest){
        ErrorResponseDto errorResponse = new ErrorResponseDto();
        errorResponse.setApiPath(webRequest.getDescription(false));
        errorResponse.setErrorMessage(exception.getMessage());
        errorResponse.setErrorCode(HttpStatus.NOT_FOUND);
        errorResponse.setErrorTime(LocalDateTime.now());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> handleUserAlreadyExistsException(UserAlreadyExistsException exception, WebRequest webRequest){
        ErrorResponseDto errorResponse = new ErrorResponseDto();
        errorResponse.setApiPath(webRequest.getDescription(false));
        errorResponse.setErrorMessage(exception.getMessage());
        errorResponse.setErrorCode(HttpStatus.BAD_REQUEST);
        errorResponse.setErrorTime(LocalDateTime.now());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoFlightAvailableException.class)
    public ResponseEntity<ErrorResponseDto> handleNoFlightAvailableException(NoFlightAvailableException exception, WebRequest webRequest){
        ErrorResponseDto errorResponse = new ErrorResponseDto();
        errorResponse.setApiPath(webRequest.getDescription(false));
        errorResponse.setErrorMessage(exception.getMessage());
        errorResponse.setErrorCode(HttpStatus.NOT_FOUND);
        errorResponse.setErrorTime(LocalDateTime.now());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UnableToDeleteFlightException.class)
    public ResponseEntity<ErrorResponseDto> handleUnableToDeleteFlightException(UnableToDeleteFlightException exception, WebRequest webRequest){
        ErrorResponseDto errorResponse = new ErrorResponseDto();
        errorResponse.setApiPath(webRequest.getDescription(false));
        errorResponse.setErrorMessage(exception.getMessage());
        errorResponse.setErrorCode(HttpStatus.BAD_REQUEST);
        errorResponse.setErrorTime(LocalDateTime.now());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        Map<String, String> validationErrors = new HashMap<>();
        List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();

        allErrors.forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();

            validationErrors.put(fieldName, message);
        });
        return new ResponseEntity<>(validationErrors, HttpStatus.BAD_REQUEST);
    }
}
