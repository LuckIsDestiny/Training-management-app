package com.training_management_app.Training_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Training {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long trainingId;

    @Column(nullable = false)
    private String trainingName;

    @Column(nullable = false)
    private String vendorName;

    @ElementCollection
    @NotBlank
    private List<String> skills;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DurationType durationType;

    @Column(nullable = false)
    private long duration;

    @FutureOrPresent
    @Column(nullable = false)
    private LocalDate startDate;

    @Future
    private LocalDate endDate;

    private long noOfBatches;
    private long studentsPerBatch;

    @Enumerated(EnumType.STRING)
    private StudentType studentType;

    @Column(nullable = false)
    private double budget;
    private String TOC;
    private String organizationName;
    private String POC;

    @Email
    private String email;

    @Pattern(regexp = "[0-9]{10}")
    private long phoneNo;

    @Enumerated(EnumType.STRING)
    private StatusType status;


    public enum DurationType{
        HOURS, DAYS, MONTHS
    }

    public enum StudentType{
        LATERAL, FRESHER
    }

    public enum StatusType{
        ON_HOLD, IN_PROGRESS, COMPLETED, UPCOMING
    }

//    @PrePersist
//    @PreUpdate
//    public void prePersist() {
//        if (this.endDate == null) {
//            if (this.duration > 0 && this.startDate != null && this.durationType != null) {
//                switch (this.durationType) {
//                    case HOURS:
//                        LocalDateTime startDateTime = this.startDate.atStartOfDay(); // Convert to LocalDateTime at the start of the day
//                        LocalDateTime endDateTime = startDateTime.plusHours(this.duration);
//                        this.endDate = endDateTime.toLocalDate(); // Convert back to LocalDate
//                        break;
//                    case DAYS:
//                        this.endDate = this.startDate.plusDays(this.duration);
//                        break;
//                    case MONTHS:
//                        this.endDate = this.startDate.plusMonths(this.duration);
//                        break;
//                }
//            } else {
//                // You could throw an exception or log a message if the duration is invalid.
//                throw new IllegalArgumentException("Invalid duration or start date");
//            }
//        }
//    }

}
