package com.prajwal.TrainBookingApp.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.context.annotation.Role;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Train {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String trainName;
    private String source;
    private String destination;
    private int totalSeats;
    private int availableSeats;

}

