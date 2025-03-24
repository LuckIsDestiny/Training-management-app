package com.training_management_app.Vendor_service.controller;

import com.training_management_app.Vendor_service.dto.ResponseDTO;
import com.training_management_app.Vendor_service.model.Vendor;
import com.training_management_app.Vendor_service.service.VendorService;
import jakarta.ws.rs.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vendor")
public class VendorController {

    @Autowired
    private VendorService vendorService;

    @PostMapping("/addVendor")
    public ResponseEntity<Vendor> addVendor(@RequestBody Vendor vendor){
        return new ResponseEntity<>(vendorService.addVendor(vendor), HttpStatus.CREATED);
    }

    @GetMapping("/getVendor/{vendorName}")
    public ResponseEntity<ResponseDTO> getVendor(@PathVariable String vendorName){
        return new ResponseEntity<>(vendorService.getVendor(vendorName), HttpStatus.OK);
    }
}
