package com.training_management_app.Trainer_service.service;

import com.training_management_app.Trainer_service.model.Trainer;
import com.training_management_app.Trainer_service.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrainerService {

    @Autowired
    private TrainerRepository trainerRepository;

    public Trainer createTrainer(Trainer trainer){
        return trainerRepository.save(trainer);
    }

//    public List<Trainer> getAvailableTrainers(double budget, LocalDate startDate, LocalDate endDate) {
//        return trainerRepository.findAvailableTrainersWithinSalaryAndDateRange(budget, startDate, endDate);
//    }

    public List<Trainer> getAvailableTrainers(double budget, LocalDate startDate, LocalDate endDate, List<String> skills) {
        List<Trainer> allTrainers = trainerRepository.findAll();

        return allTrainers.stream()
                .filter(trainer -> trainer.isAvailable()
                        && budget >= trainer.getMinSalary()
                        && budget <= trainer.getMaxSalary()
                        && !(startDate.isAfter(trainer.getAvailableTill()) || endDate.isBefore(trainer.getAvailableFrom()))
                        && trainer.getSkills().stream().anyMatch(skills::contains))
                .collect(Collectors.toList());
    }

}
