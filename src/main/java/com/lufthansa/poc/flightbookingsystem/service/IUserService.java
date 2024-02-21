package com.lufthansa.poc.flightbookingsystem.service;

import com.lufthansa.poc.flightbookingsystem.dto.UserDto;

public interface IUserService {

    String createUser(UserDto userDto);

    UserDto findUserByEmail(String email);

    boolean updateUserDetails(UserDto userDto);
}
