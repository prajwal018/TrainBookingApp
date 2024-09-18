package com.prajwal.TrainBookingApp.repository;

import com.prajwal.TrainBookingApp.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByUsername(String username);

    @Query(value = "SELECT * FROM Users u WHERE u.role = :role AND u.api_key = :api_key", nativeQuery = true)
    Optional<Users> findByRoleAndApiKey(String role, String api_key);

    Optional<Users> findByApiKey(String apiKey);

}
