package com.derick.application.controllers;

import com.derick.application.controllers.util.UserUtil;
import com.derick.dto.quotation.AddQuotationDto;
import com.derick.dto.quotation.QuotationResponse;
import com.derick.service.IPrescriptionQuotationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Validated
public class QuotationController {

    @Autowired
    UserUtil userUtil;

    @Autowired
    IPrescriptionQuotationService prescriptionQuotationService;

    @PostMapping("/api/quotation/send")
    public ResponseEntity<QuotationResponse> sendQuotation(@RequestBody AddQuotationDto quotationDto, HttpServletRequest request, HttpServletResponse response){
        QuotationResponse quotationResponse=new QuotationResponse();
        quotationResponse.setResponse("failed");
        try {
            quotationResponse=prescriptionQuotationService.sendQuotation(quotationDto);

            return ResponseEntity.ok(quotationResponse);
        }catch (Exception e){
            e.printStackTrace();
        }

        return ResponseEntity.ok(quotationResponse);
    }

    @GetMapping("/api/quotation/{id}")
    public ResponseEntity<QuotationResponse> viewQuotation(@PathVariable int id, HttpServletRequest request, HttpServletResponse response){
        QuotationResponse quotationResponse=new QuotationResponse();
        quotationResponse.setResponse("failed");
        try {
            quotationResponse=prescriptionQuotationService.viewQuotation(id);

            return ResponseEntity.ok(quotationResponse);
        }catch (Exception e){
            e.printStackTrace();
        }

        return ResponseEntity.ok(quotationResponse);
    }

    @DeleteMapping("/api/quotation/{id}")
    public ResponseEntity<QuotationResponse> deleteQuotation(@PathVariable int id, HttpServletRequest request, HttpServletResponse response){
        QuotationResponse quotationResponse=new QuotationResponse();
        quotationResponse.setResponse("failed");
        try {
            quotationResponse=prescriptionQuotationService.deleteQuotation(id);

            return ResponseEntity.ok(quotationResponse);
        }catch (Exception e){
            e.printStackTrace();
        }

        return ResponseEntity.ok(quotationResponse);
    }

    @GetMapping("/api/quotation/customer/{id}")
    public ResponseEntity<QuotationResponse> viewCustomerQuotations(@PathVariable int id, HttpServletRequest request, HttpServletResponse response){
        QuotationResponse quotationResponse=new QuotationResponse();
        quotationResponse.setResponse("failed");
        try {
            quotationResponse=prescriptionQuotationService.viewCustomerQuotations(id);

            return ResponseEntity.ok(quotationResponse);
        }catch (Exception e){
            e.printStackTrace();
        }

        return ResponseEntity.ok(quotationResponse);
    }

    @GetMapping("/api/quotation/pharmacy/{id}")
    public ResponseEntity<QuotationResponse> viewPharmacyQuotations(@PathVariable int id, HttpServletRequest request, HttpServletResponse response){
        QuotationResponse quotationResponse=new QuotationResponse();
        quotationResponse.setResponse("failed");
        try {
            quotationResponse=prescriptionQuotationService.viewPharmacyQuotations(id);

            return ResponseEntity.ok(quotationResponse);
        }catch (Exception e){
            e.printStackTrace();
        }

        return ResponseEntity.ok(quotationResponse);
    }

    @DeleteMapping("/api/quotation/customer/{id}")
    public ResponseEntity<QuotationResponse> deleteCustomerQuotations(@PathVariable int id, HttpServletRequest request, HttpServletResponse response){
        QuotationResponse quotationResponse=new QuotationResponse();
        quotationResponse.setResponse("failed");
        try {
            quotationResponse=prescriptionQuotationService.viewCustomerQuotations(id);

            return ResponseEntity.ok(quotationResponse);
        }catch (Exception e){
            e.printStackTrace();
        }

        return ResponseEntity.ok(quotationResponse);
    }

}
