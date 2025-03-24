package com.training_management_app.Vendor_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VendorDTO {
    private Long vendorId;
    private String vendorName;
    private String vendorLocation;
    private String vendorIndustry;
    private String vendorPhoneNo;
    private String POC;
}
