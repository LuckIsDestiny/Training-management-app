package com.training_management_app.Training_service.service;

import com.training_management_app.Training_service.dto.ResponseDTO;
import com.training_management_app.Training_service.dto.TrainerDTO;
import com.training_management_app.Training_service.dto.TrainingDTO;
import com.training_management_app.Training_service.model.Training;
import com.training_management_app.Training_service.repository.TrainingRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TrainingService {

    @Autowired
    private TrainingRepository trainingRepository;

    @Autowired
    private WebClient webClient;

    public Training addTraining(Training training){
        return trainingRepository.save(training);
    }

    public List<Training> getTrainingByVendorName(String vendorName){
        return trainingRepository.findByVendorName(vendorName);
    }

    public List<Training> getTrainingByOrganizationName(String organizationName){
        return trainingRepository.findByOrganizationName(organizationName);
    }

    public List<Training> getTrainingsBySkills(List<String> skills){
        return trainingRepository.findBySkills(skills);
    }

    public List<Training> getTrainingsByStartDate(LocalDate start){
        return trainingRepository.findByStartDate(start);
    }

    public List<Training> getTrainingsByStartDateBetween(LocalDate start, LocalDate end) {
        return trainingRepository.findByStartDateBetween(start, end);
    }

    @CircuitBreaker(name = "trainerService", fallbackMethod = "fallbackGetTrainers")
    public ResponseDTO getTrainingWithEligibleTrainers(Long trainingId) {
        Optional<Training> trainingOpt = trainingRepository.findById(trainingId);
        if (trainingOpt.isEmpty()) {
            throw new RuntimeException("Training not found");
        }

        ResponseDTO responseDTO = new ResponseDTO();
        Training training = trainingOpt.get();
        TrainingDTO trainingDTO = mapToTrainingDTO(training);

        List<TrainerDTO> trainerDTOList = webClient
                .get()
                .uri("http://localhost:9099/trainer/available/" +
                        training.getBudget() +"/"+ training.getStartDate() +"/"+ training.getEndDate() +"/"+
                        String.join(",", training.getSkills()))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<TrainerDTO>>() {})
                .block();

        responseDTO.setTraining(trainingDTO);
        responseDTO.setTrainer(trainerDTOList);
        return responseDTO;
    }

    public ResponseDTO fallbackGetTrainers(Long trainingId, Throwable throwable) {
        return new ResponseDTO();
    }

    private TrainingDTO mapToTrainingDTO(Training training) {
        return new TrainingDTO(
                training.getTrainingId(),
                training.getTrainingName(),
                training.getVendorName(),
                training.getSkills(),
                training.getDurationType(),
                training.getDuration(),
                training.getStartDate(),
                training.getEndDate(),
                training.getNoOfBatches(),
                training.getStudentsPerBatch(),
                training.getStudentType(),
                training.getBudget(),
                training.getTOC(),
                training.getOrganizationName(),
                training.getPOC(),
                training.getEmail(),
                training.getPhoneNo(),
                training.getStatus()
        );
    }
}
