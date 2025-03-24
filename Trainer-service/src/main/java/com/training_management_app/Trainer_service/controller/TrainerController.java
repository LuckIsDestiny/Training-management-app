package com.training_management_app.Trainer_service.controller;

import com.training_management_app.Trainer_service.model.Trainer;
import com.training_management_app.Trainer_service.service.TrainerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

        import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/trainer")
public class TrainerController {

    @Autowired
    private TrainerService trainerService;

    @PostMapping
    public ResponseEntity<Trainer> createTrainer(@RequestBody Trainer trainer){
        return ResponseEntity.status(HttpStatus.CREATED).body(trainerService.createTrainer(trainer));
    }

    @GetMapping("/available/{budget}/{startDate}/{endDate}/{skills}")
    public ResponseEntity<List<Trainer>> findAvailableTrainers(
            @Valid @PathVariable("budget") double budget,
            @PathVariable("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @PathVariable("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @PathVariable("skills") List<String> skills) {
        return new ResponseEntity<>(trainerService.getAvailableTrainers(budget, startDate, endDate, skills), HttpStatus.OK);
    }
}
