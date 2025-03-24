package com.training_management_app.Trainer_service.repository;

import com.training_management_app.Trainer_service.model.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface TrainerRepository extends JpaRepository<Trainer, Long> {

//    @Query("SELECT t FROM Trainer t " +
//            "WHERE :budget BETWEEN t.minSalary AND t.maxSalary " +
//            "AND t.availableFrom <= :startDate " +
//            "AND t.availableTill >= :endDate " +
//            "AND t.available = true")
//    List<Trainer> findAvailableTrainersWithinSalaryAndDateRange(
//            @Param("budget") double budget,
//            @Param("startDate") LocalDate startDate,
//            @Param("endDate") LocalDate endDate);

}
