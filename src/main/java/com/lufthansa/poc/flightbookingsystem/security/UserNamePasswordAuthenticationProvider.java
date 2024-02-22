package com.lufthansa.poc.flightbookingsystem.security;

import com.lufthansa.poc.flightbookingsystem.entity.Booking;
import com.lufthansa.poc.flightbookingsystem.entity.Roles;
import com.lufthansa.poc.flightbookingsystem.entity.UserEntity;
import com.lufthansa.poc.flightbookingsystem.exception.BadCredentialsException;
import com.lufthansa.poc.flightbookingsystem.repository.BookingRepository;
import com.lufthansa.poc.flightbookingsystem.repository.FlightRepository;
import com.lufthansa.poc.flightbookingsystem.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class UserNamePasswordAuthenticationProvider implements AuthenticationProvider {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();
        UserEntity user = userRepository.findByEmail(email);
        if(null!=user && passwordEncoder.matches(password, user.getPwd())){
            return new UsernamePasswordAuthenticationToken(user.getEmail(), null, getGrantedAuthorities(user.getRole()));
        }else{
            throw new BadCredentialsException("Invalid Credentials");
        }
    }

    private List<GrantedAuthority> getGrantedAuthorities(Roles roles) {
        List<GrantedAuthority> listOfGrantedAuthorities = new ArrayList<>();
        listOfGrantedAuthorities.add(new SimpleGrantedAuthority("ROLE_"+roles.getRoleName()));
        return listOfGrantedAuthorities;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
