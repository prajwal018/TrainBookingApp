package com.prajwal.TrainBookingApp.record.responses;

import com.prajwal.TrainBookingApp.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    private String message;
    private String token;
}
