package com.training_management_app.Training_service.dto;

import com.training_management_app.Training_service.model.Training;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainingDTO {
    private Long trainingId;
    private String trainingName;
    private String vendorName;
    private List<String> skills;
    private Training.DurationType durationType;
    private long duration;
    private LocalDate startDate;
    private LocalDate endDate;
    private long noOfBatches;
    private long studentsPerBatch;
    private Training.StudentType studentType;
    private double budget;
    private String TOC;
    private String organizationName;
    private String POC;
    private String email;
    private long phoneNo;
    private Training.StatusType status;
}
