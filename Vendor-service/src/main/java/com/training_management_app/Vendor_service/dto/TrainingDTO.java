package com.training_management_app.Vendor_service.dto;

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
    private DurationType durationType;
    private long duration;
    private LocalDate startDate;
    private LocalDate endDate;
    private long noOfBatches;
    private long studentsPerBatch;
    private StudentType studentType;
    private double budget;
    private String TOC;
    private String organizationName;
    private String POC;
    private String email;
    private long phoneNo;
    private StatusType status;
    private List<TrainerDTO> trainerDTOList;

    public enum DurationType{
        HOURS, DAYS, MONTHS
    }

    public enum StudentType{
        LATERAL, FRESHER
    }

    public enum StatusType{
        ON_HOLD, IN_PROGRESS, COMPLETED, UPCOMING
    }
}
