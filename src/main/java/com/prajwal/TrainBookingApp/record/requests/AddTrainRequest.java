package com.prajwal.TrainBookingApp.record.requests;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddTrainRequest {

    private String trainName;
    private String source;
    private String destination;
        private int totalSeats;

}

