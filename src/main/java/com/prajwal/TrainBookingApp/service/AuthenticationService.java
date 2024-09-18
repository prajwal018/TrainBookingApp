package com.prajwal.TrainBookingApp.service;

import com.prajwal.TrainBookingApp.configuration.utils.JwtService;
import com.prajwal.TrainBookingApp.model.Role;
import com.prajwal.TrainBookingApp.model.Users;
import com.prajwal.TrainBookingApp.record.requests.AuthenticationRequest;
import com.prajwal.TrainBookingApp.record.responses.AuthenticationResponse;
import com.prajwal.TrainBookingApp.record.requests.RegisterRequest;
import com.prajwal.TrainBookingApp.record.responses.RegistrationResponse;
import com.prajwal.TrainBookingApp.repository.UserRepository;
import com.prajwal.TrainBookingApp.utils.ApiKeyUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public RegistrationResponse register(RegisterRequest request) {
        Optional<Users> exUser = repository.findByUsername(request.getUsername());

        if (exUser.isPresent() && exUser.get().getUsername().equals(request.getUsername())) {
            throw new UsernameNotFoundException("User exists already");
        }
//        String hashedApiKey=null;
        String hashedApiKey = null;
        if (request.getRole() == Role.ADMIN) {
            String rawApiKey = ApiKeyUtil.generateApiKey();
            hashedApiKey = ApiKeyUtil.hashApiKey(rawApiKey);
        }
        var user = Users.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .apiKey(hashedApiKey)
                .build();

        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return RegistrationResponse.builder()
                .message("User registration successful")
                .apikey(hashedApiKey)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user = repository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .message("Login successful")
                .token(jwtToken)
                .build();
    }
}
