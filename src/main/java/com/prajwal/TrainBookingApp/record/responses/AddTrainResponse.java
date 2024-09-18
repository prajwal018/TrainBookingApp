package com.prajwal.TrainBookingApp.record.responses;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddTrainResponse {

    private String message;
    private long trainNumber;
    private String trainName;

}

