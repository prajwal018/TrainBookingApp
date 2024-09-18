package com.prajwal.TrainBookingApp.service;

import com.prajwal.TrainBookingApp.model.Booking;
import com.prajwal.TrainBookingApp.model.Train;
import com.prajwal.TrainBookingApp.record.requests.BookingRequest;
import com.prajwal.TrainBookingApp.record.responses.BookingDetailsResponse;
import com.prajwal.TrainBookingApp.record.responses.SeatResponse;
import com.prajwal.TrainBookingApp.repository.BookingRepository;
import com.prajwal.TrainBookingApp.repository.TrainRepository;
import com.prajwal.TrainBookingApp.repository.UserRepository;
import jakarta.persistence.LockModeType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;

    private final TrainRepository trainRepository;
    private final UserRepository userRepository;

    @Transactional
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    public SeatResponse bookSeats(BookingRequest bookingRequest) {
        Optional<Train> existsTrain = trainRepository.findById(bookingRequest.getTrainNumber());

        if (existsTrain.isPresent()) {
            Train train = existsTrain.get();
            if (train.getAvailableSeats() >= bookingRequest.getSeats()) {
                train.setAvailableSeats(train.getAvailableSeats() - bookingRequest.getSeats());
                trainRepository.save(train);

                Booking booking = new Booking();
                booking.setTrain(train);
                booking.setUser(userRepository.findById(bookingRequest.getUserid()).get());
                int[] seats = new int[bookingRequest.getSeats()];
                for (int j=0 , i = bookingRequest.getSeats(); i > 0 ; i--,j++) {
                seats[j] = (train.getAvailableSeats()-i);
                }
                booking.setSeatNumber(seats);
                booking.setBookingTime(LocalDateTime.now());
                bookingRepository.save(booking);

                return SeatResponse.builder()
                        .trainName(booking.getTrain().getTrainName())
                        .seatsBooked(seats.length)
                        .trainNumber(booking.getTrain().getId()).build();
            } else {
                throw new RuntimeException("No seats available");
            }
        }
        return null;
    }

    public BookingDetailsResponse getBookings(Long id) {
        Optional<Booking> existsBooking = bookingRepository.findById(id);
        if (existsBooking.isPresent()) {
            Booking booking = existsBooking.get();
            return BookingDetailsResponse.builder()
                    .bookingId(booking.getId())
                    .trainName(booking.getTrain().getTrainName())
                    .trainNumber(booking.getTrain().getId())
                    .source(booking.getTrain().getSource())
                    .bookedBy(booking.getUser().getUsername())
                    .bookingTime(booking.getBookingTime())
                    .destination(booking.getTrain().getDestination())
                    .seatNumber(booking.getSeatNumber())
                    .seatsBooked(booking.getSeatNumber().length).build();

        } else {
            throw new RuntimeException("No seats available");
        }
    }
}
