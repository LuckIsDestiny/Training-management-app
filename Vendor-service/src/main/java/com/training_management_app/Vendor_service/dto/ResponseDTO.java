package com.training_management_app.Vendor_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO {
    private VendorDTO vendor;
    private List<TrainingDTO> training;
}
