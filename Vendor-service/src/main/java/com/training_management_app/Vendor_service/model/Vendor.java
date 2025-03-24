package com.training_management_app.Vendor_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vendor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vendorId;

    @Column(unique = true)
    private String vendorName;

    private String vendorLocation;
    private String vendorIndustry;
    private String vendorPhoneNo;
    private String POC;
}
