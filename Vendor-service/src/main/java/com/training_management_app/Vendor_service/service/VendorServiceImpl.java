package com.training_management_app.Vendor_service.service;

import com.training_management_app.Vendor_service.dto.ResponseDTO;
import com.training_management_app.Vendor_service.dto.TrainerDTO;
import com.training_management_app.Vendor_service.dto.TrainingDTO;
import com.training_management_app.Vendor_service.dto.VendorDTO;
import com.training_management_app.Vendor_service.model.Vendor;
import com.training_management_app.Vendor_service.repository.VendorRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class VendorServiceImpl implements VendorService {

    @Autowired
    private VendorRepository vendorRepository;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Override
    public Vendor addVendor(Vendor vendor) {
        return vendorRepository.save(vendor);
    }

    @Override
    @CircuitBreaker(name = "vendorService", fallbackMethod = "fallbackGetVendor")
    public ResponseDTO getVendor(String vendorName) {
        ResponseDTO responseDTO = new ResponseDTO();
        Vendor vendor = vendorRepository.findByVendorName(vendorName)
                .orElseThrow(() -> new RuntimeException("Vendor Not Found"));
        VendorDTO vendorDTO = mapToVendorDTO(vendor);

        List<TrainingDTO> trainingDTOList = webClientBuilder.baseUrl("http://localhost:9098")
                .build()
                .get()
                .uri("/training/" + vendor.getVendorName())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<TrainingDTO>>() {})
                .block();

        if (trainingDTOList == null) throw new AssertionError();
        for(TrainingDTO trainingDTO: trainingDTOList){
            List<TrainerDTO> trainerDTOList = webClientBuilder.baseUrl("http://localhost:9099")
                    .build()
                    .get()
                    .uri("/trainer/available/" + trainingDTO.getBudget() +"/"+ trainingDTO.getStartDate() +"/"+ trainingDTO.getEndDate() +"/"+
                            String.join(",", trainingDTO.getSkills()))
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<List<TrainerDTO>>() {})
                    .block();
            trainingDTO.setTrainerDTOList(trainerDTOList);
        }
        responseDTO.setTraining(trainingDTOList);
        return responseDTO;
    }

    public ResponseDTO fallbackGetVendor(String vendorName, Throwable throwable) {
        // Handle the fallback logic, e.g., return a default response or log the error
        return new ResponseDTO(); // Return an empty response or a default response
    }

    private VendorDTO mapToVendorDTO(Vendor vendor){
        return new VendorDTO(
                vendor.getVendorId(),
                vendor.getVendorName(),
                vendor.getVendorLocation(),
                vendor.getVendorIndustry(),
                vendor.getVendorPhoneNo(),
                vendor.getPOC()
        );
    }
}
