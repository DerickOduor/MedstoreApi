package com.derick.application.controllers;

import com.derick.dto.medstore.AddMedicineDto;
import com.derick.dto.medstore.AddMedicineResponse;
import com.derick.dto.medstore.ViewMedicineDto;
import com.derick.dto.medstore.ViewMedicineResponse;
import com.derick.service.IMedicineService;
import com.derick.utils.LogFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Validated
public class MedicineController {

    @Autowired
    IMedicineService medicineService;

    @Autowired
    LogFile logFile;

    @GetMapping("/api/medicine/get")
    public ResponseEntity<ViewMedicineResponse> getMedicine(HttpServletRequest request, HttpServletResponse response){
        ViewMedicineResponse viewMedicineResponse=new ViewMedicineResponse();
        viewMedicineResponse.setResponse("failed");

        try{
            viewMedicineResponse=medicineService.getMedicine();
            return ResponseEntity.ok(viewMedicineResponse);
        }catch (Exception e){
            logFile.error(e);
            e.printStackTrace();
        }

        return ResponseEntity.ok(viewMedicineResponse);
    }

    @GetMapping("/api/medicine/get/{id}")
    public ResponseEntity<ViewMedicineResponse> getMedicine(@PathVariable int id, HttpServletRequest request, HttpServletResponse response){
        ViewMedicineResponse viewMedicineResponse=new ViewMedicineResponse();
        viewMedicineResponse.setResponse("failed");

        try{
            viewMedicineResponse=medicineService.getMedicine(id);
            return ResponseEntity.ok(viewMedicineResponse);
        }catch (Exception e){
            logFile.error(e);
            e.printStackTrace();
        }

        return ResponseEntity.ok(viewMedicineResponse);
    }

    @GetMapping("/api/medicine/pharmacy/{id}")
    public ResponseEntity<ViewMedicineResponse> getPharmacyMedicine(@PathVariable int id, HttpServletRequest request, HttpServletResponse response){
        ViewMedicineResponse viewMedicineResponse=new ViewMedicineResponse();
        viewMedicineResponse.setResponse("failed");

        try{
            viewMedicineResponse=medicineService.getPharmacyMedicine(id);
            return ResponseEntity.ok(viewMedicineResponse);
        }catch (Exception e){
            e.printStackTrace();
            logFile.error(e);
        }

        return ResponseEntity.ok(viewMedicineResponse);
    }

    @PostMapping("/api/medicine/search")
    public ResponseEntity<ViewMedicineResponse> searchMedicine(@RequestBody String key, HttpServletRequest request, HttpServletResponse response){
        ViewMedicineResponse viewMedicineResponse=new ViewMedicineResponse();
        viewMedicineResponse.setResponse("failed");

        try{
            viewMedicineResponse=medicineService.getMedicine(key);
            return ResponseEntity.ok(viewMedicineResponse);
        }catch (Exception e){
            logFile.error(e);
            e.printStackTrace();
        }

        return ResponseEntity.ok(viewMedicineResponse);
    }

    @PostMapping("/api/medicine/add")
    public ResponseEntity<ViewMedicineResponse> addMedicine(@RequestBody AddMedicineDto addMedicineDto, HttpServletRequest request, HttpServletResponse response){
        ViewMedicineResponse viewMedicineResponse=new ViewMedicineResponse();
        viewMedicineResponse.setResponse("failed");

        try{
            viewMedicineResponse=medicineService.addMedicine(addMedicineDto);
            return ResponseEntity.ok(viewMedicineResponse);
        }catch (Exception e){
            logFile.error(e);
            e.printStackTrace();
        }

        return ResponseEntity.ok(viewMedicineResponse);
    }

    @PutMapping("/api/medicine/update")
    public ResponseEntity<AddMedicineResponse> updateMedicine(@RequestBody ViewMedicineDto viewMedicineDto, HttpServletRequest request, HttpServletResponse response){
        AddMedicineResponse viewMedicineResponse=new AddMedicineResponse();
        viewMedicineResponse.setResponse("failed");

        try{
            viewMedicineResponse=medicineService.updateMedicine(viewMedicineDto);
            return ResponseEntity.ok(viewMedicineResponse);
        }catch (Exception e){
            logFile.error(e);
            e.printStackTrace();
        }

        return ResponseEntity.ok(viewMedicineResponse);
    }

}
