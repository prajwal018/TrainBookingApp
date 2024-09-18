package com.prajwal.TrainBookingApp.record.requests;

import jakarta.persistence.Entity;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class BookingRequest {

    private long trainNumber;
    public long userid;
    private int seats;

}

