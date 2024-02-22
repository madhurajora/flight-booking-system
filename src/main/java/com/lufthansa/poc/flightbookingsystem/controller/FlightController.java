package com.lufthansa.poc.flightbookingsystem.controller;

import com.lufthansa.poc.flightbookingsystem.dto.FlightDto;
import com.lufthansa.poc.flightbookingsystem.dto.ResponseDto;
import com.lufthansa.poc.flightbookingsystem.service.IFlightService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Validated
@RequestMapping("/api")
@Slf4j
public class FlightController {

    private IFlightService iFlightService;

    @PostMapping("/flight")
    public ResponseEntity<ResponseDto> createFlight(@Valid @RequestBody FlightDto flightDto){
        log.debug("Inside createFlight(), Request for creating a Flight is received: {} "+flightDto);
        long flightNumber = iFlightService.createFlight(flightDto);
        String message = "Flight got created with Flight Number: "+flightNumber;
        ResponseDto response = new ResponseDto("201", flightNumber ,message);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/flight")
    public ResponseEntity<List<FlightDto>> findFlightFromAndTo(@Valid @RequestParam String from, @Valid @RequestParam String to){
        log.debug("Inside findFlightFromAndTo(@Valid @RequestParam String from, @Valid @RequestParam String to), " +
                "Request for fetching a Flight from: {} and to: {} "+from, to);
        List<FlightDto> byFlightFromAndTo = iFlightService.findByFlightFromAndTo(from, to);
        return new ResponseEntity<>(byFlightFromAndTo, HttpStatus.OK);
    }

    @GetMapping("/flight/flightNumber")
    public ResponseEntity<FlightDto> findFlightByFlightNumber(@Valid @RequestParam long flightNumber){
        log.debug("Inside findFlightByFlightNumber(@Valid @RequestParam long flightNumber), " +
                "Request for fetching a Flight by Flight Number: {} is received "+flightNumber);
        FlightDto flightDto = iFlightService.findByFlightNumber(flightNumber);
        return new ResponseEntity<>(flightDto, HttpStatus.OK);
    }

    @PutMapping("/flight/{flightNumber}")
    public ResponseEntity<ResponseDto> updateFlightDetails(@Valid @PathVariable long flightNumber, @Valid @RequestBody FlightDto flightDto){
        log.debug("Inside updateFlightDetails() " +
                "Request for updating Flight Details : {} is received"+flightNumber);
        iFlightService.findByFlightNumber(flightNumber);
        boolean isUpdated = iFlightService.updateFlightDetails(flightDto, flightNumber);
        if(isUpdated){
            String message = "Flight Details of flight number: "+flightNumber+" is updated.";
            ResponseDto response = new ResponseDto("201", flightNumber ,message);
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        }else{
            String message = "Oops! Flight Details of flight number: "+flightNumber+" is not updated.";
            ResponseDto response = new ResponseDto("500", flightNumber ,message);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/flight/{flightNumber}")
    public ResponseEntity<ResponseDto> deleteFlightByFlightNumber(@PathVariable long flightNumber){
        log.debug("Inside deleteFlightByFlightNumber() " +
                "Request for deleting Flight Details : {} is received"+flightNumber);
        if(iFlightService.deleteFlight(flightNumber)){
            String message = "Flight Details of flight number: "+flightNumber+" is deleted.";
            ResponseDto response = new ResponseDto("200", flightNumber ,message);
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        }else{
            String message = "Oops! Flight Details of flight number: "+flightNumber+" is not deleted.";
            ResponseDto response = new ResponseDto("500", flightNumber ,message);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
