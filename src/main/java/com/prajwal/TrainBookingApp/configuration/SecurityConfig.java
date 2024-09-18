package com.prajwal.TrainBookingApp.configuration;

import com.prajwal.TrainBookingApp.configuration.filter.ApiKeyFilter;
import com.prajwal.TrainBookingApp.configuration.filter.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.prajwal.TrainBookingApp.model.Permission.*;
import static com.prajwal.TrainBookingApp.model.Role.ADMIN;
import static com.prajwal.TrainBookingApp.model.Role.USER;
import static org.springframework.http.HttpMethod.*;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final ApplicationConfig applicationConfig;
    private final ApiKeyFilter apiKeyFilter;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Configuring security for the application
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**").permitAll()

                        .requestMatchers("/users/**").hasAnyRole(ADMIN.name(), USER.name())

                        .requestMatchers(GET, "/users/**").hasAnyAuthority(ADMIN_READ.name(), USER_READ.name())
                        .requestMatchers(POST, "/users/**").hasAnyAuthority(ADMIN_CREATE.name(), USER_CREATE.name())
                        .requestMatchers(PUT, "/users/**").hasAnyAuthority(ADMIN_DELETE.name(), USER_CREATE.name())
                        .requestMatchers(DELETE, "/users/**").hasAnyAuthority(ADMIN_UPDATE.name(), USER_CREATE.name())

//                        .requestMatchers("/admin/**").hasRole(ADMIN.name())
//
//                        .requestMatchers(GET, "/admin/**").hasAuthority(ADMIN_READ.name())
//                        .requestMatchers(POST, "/admin/**").hasAuthority(ADMIN_CREATE.name())
//                        .requestMatchers(PUT, "/admin/**").hasAuthority(ADMIN_DELETE.name())
//                        .requestMatchers(DELETE, "/admin/**").hasAuthority(ADMIN_UPDATE.name())

                        // All other requests must be authenticated
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(applicationConfig.authenticationProvider())
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(apiKeyFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
