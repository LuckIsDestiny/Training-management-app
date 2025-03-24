package com.training_management_app.Trainer_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Trainer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long trainerId;

    @NotNull
    private String trainerName;

    @Email
    private String email;

    @Pattern(regexp = "[0-9]{10}")
    private long phoneNo;
    private boolean available;
    private double minSalary;
    private double maxSalary;

    @FutureOrPresent
    private LocalDate availableFrom;

    @Future
    private LocalDate availableTill;

    @ElementCollection
    private List<String> skills;
}
