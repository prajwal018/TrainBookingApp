package com.prajwal.TrainBookingApp.service;

import com.prajwal.TrainBookingApp.model.Train;
import com.prajwal.TrainBookingApp.model.Users;
import com.prajwal.TrainBookingApp.record.requests.AddTrainRequest;
import com.prajwal.TrainBookingApp.record.responses.AddTrainResponse;
import com.prajwal.TrainBookingApp.record.responses.TrainResponse;
import com.prajwal.TrainBookingApp.repository.TrainRepository;
import com.prajwal.TrainBookingApp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TrainService {
    private final TrainRepository trainRepository;

    private final UserRepository userRepository;



    public ResponseEntity<AddTrainResponse> addTrain(AddTrainRequest trainRequest, String apiKey) {
        Users admin = userRepository.findByApiKey(apiKey)
                .orElseThrow(() -> new RuntimeException("Invalid API key"));

        Train newTrain = new Train();
        newTrain.setTrainName(trainRequest.getTrainName());
        newTrain.setSource(trainRequest.getSource());
        newTrain.setDestination(trainRequest.getDestination());
        newTrain.setTotalSeats(trainRequest.getTotalSeats());
        newTrain.setAvailableSeats(trainRequest.getTotalSeats());
        trainRepository.save(newTrain);

        return ResponseEntity.ok(AddTrainResponse.builder()
                .message("Train Added Successfully")
                .trainName(newTrain.getTrainName())
                .trainNumber(newTrain.getId())
                .build());
    }


    public List<TrainResponse> getTrainAvailability(String source, String destination) {
        List<Train> trains =  trainRepository.findBySourceAndDestination(source,destination);

        return trains.stream()
                .map(train -> TrainResponse.builder()
                        .trainName(train.getTrainName())
                        .trainNumber(train.getId())
                        .availableSeats(train.getAvailableSeats())
                        .build())
                .collect(Collectors.toList());
    }
}
