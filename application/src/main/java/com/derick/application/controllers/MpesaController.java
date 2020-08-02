package com.derick.application.controllers;

import com.derick.external.payment.mpesa.c2b.Register;
import com.derick.external.payment.mpesa.c2b.StkPush;
import com.derick.external.payment.mpesa.c2b.StkPushResponse;
import com.derick.external.payment.mpesa.response.QueueTimeoutResponse;
import com.derick.external.payment.mpesa.response.ValidateResponse;
import com.derick.external.payment.mpesa.response.Validation;
//import com.derick.external.payment.mpesa.response.stkpush.MpesaCallBackResponse;
import com.derick.dto.payment.mpesa.stkpush.MpesaCallBackResponse;
import com.derick.external.payment.mpesa.security.SecurityUtil;
import com.derick.service.IStkPushService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public class MpesaController {

    Gson gson=new Gson();

    @Autowired
    SecurityUtil securityUtil;

    @Autowired
    StkPush stkPush;

    @Autowired
    IStkPushService stkPushService;

    @GetMapping( value = "/api/mpesa")
    @PreAuthorize("permitAll()")
    public String launch(){
        try {
            //SecurityUtil.AccessToken();
            //Register.registerUrl();
            return stkPush.processPayment();
        }catch (Exception e){
            e.printStackTrace();
        }
        return "WELCOME";
    }

    @PostMapping( value = "/api/chezanani/return")
    @PreAuthorize("permitAll()")
    public String stkresponse(@RequestBody MpesaCallBackResponse response){
        try {
            System.out.println("MpesaCallBackResponse: "+gson.toJson(response));
            stkPushService.addStkPushResponse(response);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "WELCOME";
    }

    @PostMapping( value = "/api/chezanani/se")
    @PreAuthorize("permitAll()")
    public ValidateResponse urlconfirmation(@RequestBody Validation validation){
        ValidateResponse response=new ValidateResponse();
        response.setResultCode(0);
        response.setResultDesc("Success");
        try {
            System.out.println("VAY: "+gson.toJson(validation));
        }catch (Exception e){
            e.printStackTrace();
        }
        return response;
    }
    @PostMapping( value = "/api/chezanani/ma")
    @PreAuthorize("permitAll()")
    public ValidateResponse urlvalidation(@RequestBody Validation validation){
        ValidateResponse response=new ValidateResponse();
        response.setResultCode(0);
        response.setResultDesc("The service was accepted successfully");
        response.setThirdPartyTransID("1234567890");
        try {
            System.out.println("ETYRG: "+gson.toJson(validation));
        }catch (Exception e){
            e.printStackTrace();
        }
        return response;
    }
    @PostMapping( value = "/api/chezanani/na")
    @PreAuthorize("permitAll()")
    public QueueTimeoutResponse queueTimeout(){
        QueueTimeoutResponse response=new QueueTimeoutResponse();
        response.setResponseCode("00000000");
        response.setResponseDesc("success");
        try {
            System.out.println("RTYR: ");
        }catch (Exception e){
            e.printStackTrace();
        }
        return response;
    }
}
