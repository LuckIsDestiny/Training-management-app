package com.training_management_app.Training_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO {
    private TrainingDTO training;
    private List<TrainerDTO> trainer;
}
