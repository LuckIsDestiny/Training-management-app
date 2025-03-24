package com.training_management_app.Vendor_service.repository;

import com.training_management_app.Vendor_service.model.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VendorRepository extends JpaRepository<Vendor, Long> {
    Optional<Vendor> findByVendorName(String vendorName);
}
