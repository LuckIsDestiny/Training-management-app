package com.training_management_app.Vendor_service.service;

import com.training_management_app.Vendor_service.dto.ResponseDTO;
import com.training_management_app.Vendor_service.model.Vendor;

public interface VendorService {

    Vendor addVendor(Vendor vendor);

    ResponseDTO getVendor(String vendorName);
}
