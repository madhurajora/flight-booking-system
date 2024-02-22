package com.lufthansa.poc.flightbookingsystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception{
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests((req) ->
                        req
                                .requestMatchers(HttpMethod.POST,"/api/user").permitAll()
                                .requestMatchers("/swagger-ui/**").permitAll()
                                .requestMatchers("/api-docs/**").permitAll()
                                .requestMatchers("/api/user/**").hasAnyRole("USER","ADMIN")
                                .requestMatchers(HttpMethod.GET, "/api/flight").permitAll()
                                .requestMatchers("/api/flight/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.POST, "/api/booking").hasAnyRole("USER","ADMIN")
                                .requestMatchers(HttpMethod.GET, "/api/booking").hasRole("ADMIN"))

                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
