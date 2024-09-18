package com.prajwal.TrainBookingApp.record.responses;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SeatResponse {

    private long trainNumber;
    private String trainName;
    private int seatsBooked;

}


