package com.prajwal.TrainBookingApp.controller;

import com.prajwal.TrainBookingApp.record.requests.BookingRequest;
import com.prajwal.TrainBookingApp.record.responses.BookingDetailsResponse;
import com.prajwal.TrainBookingApp.record.responses.SeatResponse;
import com.prajwal.TrainBookingApp.record.responses.TrainResponse;
import com.prajwal.TrainBookingApp.service.BookingService;
import com.prajwal.TrainBookingApp.service.TrainService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {


    private final TrainService trainService;
    private final BookingService bookingService;

    @GetMapping("/trains/availability")
    public ResponseEntity<List<TrainResponse>> getTrainAvailability(@RequestParam String source, @RequestParam String destination) {
        List<TrainResponse> trains = trainService.getTrainAvailability(source, destination);
        return ResponseEntity.ok(trains);
    }

    @PostMapping("/bookings")
    public ResponseEntity<SeatResponse> bookSeats(@RequestBody BookingRequest bookingRequest) {
        return ResponseEntity.ok(bookingService.bookSeats(bookingRequest));
    }

    @GetMapping("/bookings/{id}")
    public ResponseEntity<BookingDetailsResponse> getBookings(@PathVariable long id) {
        BookingDetailsResponse bookings = bookingService.getBookings(id);
        return ResponseEntity.ok(bookings);
    }


}
