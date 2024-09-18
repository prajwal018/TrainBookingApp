package com.prajwal.TrainBookingApp.repository;

import com.prajwal.TrainBookingApp.model.Booking;
import com.prajwal.TrainBookingApp.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
}
