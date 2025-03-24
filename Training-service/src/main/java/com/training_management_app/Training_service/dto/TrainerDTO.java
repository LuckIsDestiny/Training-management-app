package com.training_management_app.Training_service.dto;

import jakarta.validation.constraints.Future;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainerDTO {
    private Long trainerId;
    private String trainerName;
    private String email;
    private long phoneNo;
    private boolean available;
    private double minSalary;
    private double maxSalary;
    private LocalDate availableFrom;
    private LocalDate availableTill;
}
