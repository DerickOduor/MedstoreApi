package com.derick.application.controllers;

import com.derick.application.configuration.JwtTokenUtil;
import com.derick.application.controllers.util.UserUtil;
import com.derick.dto.prescription.NewPrescriptionDto;
import com.derick.dto.prescription.PrescriptionResponse;
import com.derick.dto.user.UserDto;
import com.derick.mapper.user.UserMapper;
import com.derick.service.IUploadPrescriptionService;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Validated
public class PrescriptionController {

    @Autowired
    IUploadPrescriptionService uploadPrescriptionService;

    @Autowired
    UserUtil userUtil;

    @PostMapping("/api/prescription")
    public ResponseEntity<PrescriptionResponse> uploadPrescription(@RequestBody NewPrescriptionDto prescription, HttpServletRequest request, HttpServletResponse response){
        PrescriptionResponse prescriptionResponse=new PrescriptionResponse();
        prescriptionResponse.setResponse("failed");
        try{
            prescription.setUserId(userUtil.getUserDetails(request).getId());
        }catch (Exception e){
            e.printStackTrace();
        }
        try {
            prescriptionResponse=uploadPrescriptionService.saveUploadPrescription(prescription);

            return ResponseEntity.ok(prescriptionResponse);
        }catch (Exception e){
            e.printStackTrace();
        }

        return ResponseEntity.ok(prescriptionResponse);
    }

    @GetMapping("/api/prescription/{id}")
    public ResponseEntity<PrescriptionResponse> getUploadPrescription(@PathVariable int id, HttpServletRequest request, HttpServletResponse response){
        PrescriptionResponse prescriptionResponse=new PrescriptionResponse();
        prescriptionResponse.setResponse("failed");
        try {
            prescriptionResponse=uploadPrescriptionService.getUploadPrescription(id);

            return ResponseEntity.ok(prescriptionResponse);
        }catch (Exception e){
            e.printStackTrace();
        }

        return ResponseEntity.ok(prescriptionResponse);
    }

    @GetMapping("/api/prescription/customer/{id}")
    public ResponseEntity<PrescriptionResponse> getCustomerUploadPrescriptions(@PathVariable int id, HttpServletRequest request, HttpServletResponse response){
        PrescriptionResponse prescriptionResponse=new PrescriptionResponse();
        prescriptionResponse.setResponse("failed");
        try {
            prescriptionResponse=uploadPrescriptionService.getAllUploadPrescription(id);

            return ResponseEntity.ok(prescriptionResponse);
        }catch (Exception e){
            e.printStackTrace();
        }

        return ResponseEntity.ok(prescriptionResponse);
    }

    @DeleteMapping("/api/prescription/delete/{id}")
    public ResponseEntity<PrescriptionResponse> deleteCustomerUploadPrescriptions(@PathVariable int id, HttpServletRequest request, HttpServletResponse response){
        PrescriptionResponse prescriptionResponse=new PrescriptionResponse();
        prescriptionResponse.setResponse("failed");
        try {
            prescriptionResponse=uploadPrescriptionService.deleteUploadPrescription(id);

            return ResponseEntity.ok(prescriptionResponse);
        }catch (Exception e){
            e.printStackTrace();
        }

        return ResponseEntity.ok(prescriptionResponse);
    }
}
