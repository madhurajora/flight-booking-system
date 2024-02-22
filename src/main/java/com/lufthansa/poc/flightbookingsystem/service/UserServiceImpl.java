package com.lufthansa.poc.flightbookingsystem.service;

import com.lufthansa.poc.flightbookingsystem.constants.FlightConstants;
import com.lufthansa.poc.flightbookingsystem.dto.UserDto;
import com.lufthansa.poc.flightbookingsystem.entity.Flight;
import com.lufthansa.poc.flightbookingsystem.entity.Roles;
import com.lufthansa.poc.flightbookingsystem.entity.UserEntity;
import com.lufthansa.poc.flightbookingsystem.exception.UserAlreadyExistsException;
import com.lufthansa.poc.flightbookingsystem.exception.ResourceNotFoundException;
import com.lufthansa.poc.flightbookingsystem.mapper.FlightMapper;
import com.lufthansa.poc.flightbookingsystem.mapper.UserMapper;
import com.lufthansa.poc.flightbookingsystem.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;


@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements IUserService{

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    
    @Override
    public String createUser(UserDto userDto) {
        log.debug("Inside createUser(), Creating new User : {} "+userDto);
        UserEntity user = UserMapper.mapToUser(userDto, new UserEntity());
        UserEntity userFromDB = userRepository.findByEmail(user.getEmail());
        if(userFromDB!=null){
            throw new UserAlreadyExistsException("User with emailId "+user.getEmail()+" already exists!");
        }
        user.setRole(new Roles(2));
        user.setCreatedAt(LocalDateTime.now());
        user.setCreatedBy(FlightConstants.ANONYMOUS);
        user.setPwd(passwordEncoder.encode(user.getPwd()));
        userRepository.save(user);
        log.info("Inside createUser(), New User created : {} "+userDto);
        return user.getEmail();
    }

    @Override
    public UserDto findUserByEmail(String email) {
        UserEntity user = userRepository.findByEmail(email);
        if(user==null){
            throw new ResourceNotFoundException("User with email "+ email+" does not exists!");
        }
        return UserMapper.mapToUserDto(user, new UserDto());
    }

    @Override
    public boolean updateUserDetails(UserDto userDto) {
        boolean isUpdated = false;
        UserEntity user = UserMapper.mapToUser(userDto, new UserEntity());
        user.setEmail(userDto.getEmail());
        user.setUpdatedAt(LocalDateTime.now());
        user.setUpdatedBy("UpdatedByAnonymous");
        UserEntity savedUser = userRepository.save(user);
        if(!ObjectUtils.isEmpty(savedUser)) {
            isUpdated = true;
        }
        log.info("updateUserDetails(String email, UserDto userDto) " +
                "Updated User Details with email : {} "+userDto.getEmail());
        return isUpdated;
    }
}
