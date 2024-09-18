package com.prajwal.TrainBookingApp.controller;

import com.prajwal.TrainBookingApp.record.requests.AddTrainRequest;
import com.prajwal.TrainBookingApp.record.responses.AddTrainResponse;
import com.prajwal.TrainBookingApp.service.TrainService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/admin")
@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
public class AdminController {


    private final TrainService trainService;


    @PostMapping("addTrain")
    @PreAuthorize("hasAuthority('admin:create')")
    public ResponseEntity<AddTrainResponse> addTrain(@RequestBody AddTrainRequest trainRequest, @RequestHeader("x-api-key") String apiKey) {
        return trainService.addTrain(trainRequest, apiKey);

    }


}
