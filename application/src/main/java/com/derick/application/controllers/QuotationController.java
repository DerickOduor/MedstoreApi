package com.derick.application.controllers;

import com.derick.application.controllers.util.UserUtil;
import com.derick.dto.chat.PushNotification;
import com.derick.dto.chat.PushNotificationData;
import com.derick.dto.chat.SendNotification;
import com.derick.dto.quotation.AddQuotationDto;
import com.derick.dto.quotation.QuotationResponse;
import com.derick.external.firebasemessaging.Fcm;
import com.derick.service.IPrescriptionQuotationService;
import com.derick.utils.LogFile;
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
    Fcm fcm;

    @Autowired
    LogFile logFile;

    @Autowired
    IPrescriptionQuotationService prescriptionQuotationService;

    @PostMapping("/api/quotation/send")
    public ResponseEntity<QuotationResponse> sendQuotation(@RequestBody AddQuotationDto quotationDto, HttpServletRequest request, HttpServletResponse response){
        QuotationResponse quotationResponse=new QuotationResponse();
        quotationResponse.setResponse("failed");
        try {
            quotationResponse=prescriptionQuotationService.sendQuotation(quotationDto);
            try{
                SendNotification notification=new SendNotification();
                notification.setTo(quotationResponse.getQuotation().getUser().getMobileToken());
                notification.setCollapse_key("type_a");
                PushNotification pushNotification=new PushNotification();
                pushNotification.setTitle("Prescription Quotation");
                pushNotification.setBody("You have received a quotation for the prescription you had requested.");
                notification.setNotification(pushNotification);
                PushNotificationData pushNotificationData=new PushNotificationData();
                pushNotificationData.setKey_1("quotation");
                pushNotificationData.setKey_2(quotationResponse.getQuotation().getId()+"");
                pushNotificationData.setTitle("Prescription Quotation");
                pushNotificationData.setBody("You have received a quotation for the prescription you had requested.");
                notification.setData(pushNotificationData);

                fcm.PushNotification(notification);
            }catch (Exception e){
                e.printStackTrace();
                logFile.error(e);
            }
            return ResponseEntity.ok(quotationResponse);
        }catch (Exception e){
            e.printStackTrace();
            logFile.error(e);
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
            logFile.error(e);
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
            logFile.error(e);
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
            logFile.error(e);
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
            logFile.error(e);
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
            logFile.error(e);
        }

        return ResponseEntity.ok(quotationResponse);
    }

}
