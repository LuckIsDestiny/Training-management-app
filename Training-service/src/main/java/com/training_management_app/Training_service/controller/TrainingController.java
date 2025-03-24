package com.training_management_app.Training_service.controller;

import com.training_management_app.Training_service.dto.ResponseDTO;
import com.training_management_app.Training_service.model.Training;
import com.training_management_app.Training_service.repository.TrainingRepository;
import com.training_management_app.Training_service.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/training")
public class TrainingController {

    @Autowired
    private TrainingService trainingService;

    @PostMapping
    public ResponseEntity<Training> addTraining(@RequestBody Training training){
        return ResponseEntity.status(HttpStatus.CREATED).body(trainingService.addTraining(training));
    }

    @GetMapping("/{vendorName}")
    public ResponseEntity<List<Training>> getTrainingByVendorName(@PathVariable String vendorName){
        return ResponseEntity.ok().body(trainingService.getTrainingByVendorName(vendorName));
    }

    @GetMapping("/withTrainers/{trainingId}")
    public ResponseEntity<ResponseDTO> getTrainingWithTrainers(@PathVariable Long trainingId){
        return ResponseEntity.ok().body(trainingService.getTrainingWithEligibleTrainers(trainingId));
    }
}
