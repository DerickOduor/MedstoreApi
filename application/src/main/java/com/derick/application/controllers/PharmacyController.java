package com.derick.application.controllers;

import com.derick.dto.pharmacy.LocationDto;
import com.derick.dto.pharmacy.PharmacyDto;
import com.derick.dto.pharmacy.PharmacyResponse;
import com.derick.external.payment.mpesa.security.SecurityUtil;
import com.derick.service.IPharmacyService;
import com.derick.utils.GeoLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
public class PharmacyController {

    @Autowired
    SecurityUtil securityUtil;

    @Autowired
    IPharmacyService pharmacyService;

    @PostMapping(value = "/api/pharmacy")
    public ResponseEntity<PharmacyResponse> addPharmacy(@RequestBody PharmacyDto pharmacyDto){
        PharmacyResponse response=new PharmacyResponse();
        response.setResponse("failed");
        try{
            response=pharmacyService.savePharmacy(pharmacyDto);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.ok(response);
    }

    @PutMapping(value = "/api/pharmacy")
    public ResponseEntity<PharmacyResponse> updatePharmacy(@RequestBody PharmacyDto pharmacyDto){
        PharmacyResponse response=new PharmacyResponse();
        response.setResponse("failed");
        try{
            response=pharmacyService.updatePharmacy(pharmacyDto);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(value = "/api/pharmacy/delete/{id}")
    public  ResponseEntity<PharmacyResponse> deletePharmacy(@PathVariable int id){
        PharmacyResponse response=new PharmacyResponse();
        response.setResponse("failed");
        try{
            response=pharmacyService.deletePharmacy(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/api/pharmacy/")
    public  ResponseEntity<PharmacyResponse> getPharmacies(){
        PharmacyResponse response=new PharmacyResponse();
        response.setResponse("failed");
        try{
            response=pharmacyService.getPharmacies();
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/api/pharmacy/location")
    public  ResponseEntity<PharmacyResponse> getPharmacies(@RequestBody LocationDto locationDto){
        PharmacyResponse response=new PharmacyResponse();
        response.setResponse("failed");
        try{
            response=pharmacyService.getPharmaciesByLocation(locationDto);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/api/pharmacy/{id}")
    public  ResponseEntity<PharmacyResponse> getPharmacy(@PathVariable int id){
        PharmacyResponse response=new PharmacyResponse();
        response.setResponse("failed");
        try{
            response=pharmacyService.getPharmacy(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/api/pharmacy/{name}")
    public  ResponseEntity<PharmacyResponse> searchPharmacy(@PathVariable String name){
        PharmacyResponse response=new PharmacyResponse();
        response.setResponse("failed");
        try{
            response=pharmacyService.getPharmacy(name);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.ok(response);
    }

}
