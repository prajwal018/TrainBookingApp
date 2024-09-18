package com.prajwal.TrainBookingApp.record.responses;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingDetailsResponse {

    private Long bookingId;
    private Long trainNumber;
    private String trainName;
    private String source;
    private String destination;
    private int seatsBooked;
    private int[] seatNumber;
    private String bookedBy;
    private LocalDateTime bookingTime;

}


