package com.training_management_app.Training_service.repository;

import com.training_management_app.Training_service.model.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface TrainingRepository extends JpaRepository<Training, Long> {
    List<Training> findByVendorName(String vendorName);
    @Query("SELECT t FROM Training t JOIN t.skills s WHERE s IN :skills")
    List<Training> findBySkills(List<String> skills);
    List<Training> findByOrganizationName(String organizationName);
    List<Training> findByStartDateBetween(LocalDate start, LocalDate end);
    List<Training> findByStartDate(LocalDate start);
}
