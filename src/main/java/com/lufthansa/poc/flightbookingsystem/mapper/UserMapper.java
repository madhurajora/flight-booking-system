package com.lufthansa.poc.flightbookingsystem.mapper;

import com.lufthansa.poc.flightbookingsystem.dto.UserDto;
import com.lufthansa.poc.flightbookingsystem.entity.UserEntity;

public class UserMapper {

    public static UserDto mapToUserDto(UserEntity user, UserDto userDto){
        userDto.setEmail(user.getEmail());
        userDto.setContactNumber(user.getContactNumber());
        userDto.setName(user.getUserName());
        userDto.setAddress(user.getAddress());
        return userDto;
    }

    public static UserEntity mapToUser(UserDto userDto, UserEntity user){
        user.setEmail(userDto.getEmail());
        user.setContactNumber(userDto.getContactNumber());
        user.setUserName(userDto.getName());
        user.setAddress(userDto.getAddress());
        return user;
    }
}
