package com.lufthansa.poc.flightbookingsystem.controller;

import com.lufthansa.poc.flightbookingsystem.dto.UserDto;
import com.lufthansa.poc.flightbookingsystem.dto.UserResponseDto;
import com.lufthansa.poc.flightbookingsystem.service.IUserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Validated
@RequestMapping("/api")
@Slf4j
public class UserController {

    private IUserService iUserService;

    @PostMapping("/user")
    public ResponseEntity<UserResponseDto> createUser(@Valid @RequestBody UserDto userDto){
        log.debug("Inside createUser(), Request for creating a User is received: {} "+userDto);
        String email = iUserService.createUser(userDto);
        String message = "User got created";
        UserResponseDto response = new UserResponseDto("201" ,email, message);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/user/{email}")
    public ResponseEntity<UserDto> fetchUserDetails(@PathVariable String email){
        log.debug("Inside fetchUserDetails(@PathVariable String email), Request for fetching User with email : {} "+email);
        UserDto userDto = iUserService.findUserByEmail(email);
        return new ResponseEntity<>(userDto, HttpStatus.ACCEPTED);
    }

    @PutMapping("/user")
    public ResponseEntity<UserResponseDto> updateUserDetails(@Valid @RequestBody UserDto userDto){
        log.debug("Inside updateUserDetails() " +
                "Request for updating User Details : {} is received"+userDto);
        boolean isUpdated = iUserService.updateUserDetails(userDto);

        if(isUpdated){
            String message = "User Details with email : "+userDto.getEmail()+" is updated.";
            UserResponseDto response = new UserResponseDto("201", userDto.getEmail() ,message);
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        }else{
            String message = "Oops! User Details with email : "+userDto.getEmail()+" are not updated.";
            UserResponseDto response = new UserResponseDto("500", userDto.getEmail() ,message);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
