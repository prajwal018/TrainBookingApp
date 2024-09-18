package com.prajwal.TrainBookingApp.controller;

import com.prajwal.TrainBookingApp.record.requests.AuthenticationRequest;
import com.prajwal.TrainBookingApp.record.requests.RegisterRequest;
import com.prajwal.TrainBookingApp.record.responses.AuthenticationResponse;
import com.prajwal.TrainBookingApp.record.responses.RegistrationResponse;
import com.prajwal.TrainBookingApp.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<RegistrationResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(service.register(request));

    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(service.authenticate(request));

    }
}
