package com.derick.application.controllers;

import com.derick.application.controllers.util.UserUtil;
import com.derick.dto.payment.option.PaymentOptionDto;
import com.derick.dto.payment.option.PaymentOptionResponse;
import com.derick.service.IPaymentOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Validated
public class PaymentOptionController {
    @Autowired
    UserUtil userUtil;

    @Autowired
    IPaymentOptionService paymentOptionService;

    @PostMapping("/api/paymentoption/option/")
    public ResponseEntity<PaymentOptionResponse> addPaymentoption(@RequestBody PaymentOptionDto paymentOptionDto, HttpServletRequest request, HttpServletResponse response)
    {
        PaymentOptionResponse paymentOptionResponse=new PaymentOptionResponse();
        paymentOptionResponse.setResponse("failed");
        try{
            paymentOptionResponse=paymentOptionService.addPaymentoption(paymentOptionDto);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.ok(paymentOptionResponse);
    }

    @PutMapping("/api/paymentoption/option/")
    public ResponseEntity<PaymentOptionResponse> updatePaymentoption(@RequestBody PaymentOptionDto paymentOptionDto, HttpServletRequest request, HttpServletResponse response)
    {
        PaymentOptionResponse paymentOptionResponse=new PaymentOptionResponse();
        paymentOptionResponse.setResponse("failed");
        try{
            paymentOptionResponse=paymentOptionService.updatePaymentoption(paymentOptionDto);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.ok(paymentOptionResponse);
    }

    @GetMapping("/api/paymentoption/{id}")
    public ResponseEntity<PaymentOptionResponse> addPaymentoption(@PathVariable int id, HttpServletRequest request, HttpServletResponse response)
    {
        PaymentOptionResponse paymentOptionResponse=new PaymentOptionResponse();
        paymentOptionResponse.setResponse("failed");
        try{
            paymentOptionResponse=paymentOptionService.getPaymentoption(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.ok(paymentOptionResponse);
    }

    @DeleteMapping("/api/paymentoption/option/{id}")
    public ResponseEntity<PaymentOptionResponse> deletePaymentoption(@PathVariable int id, HttpServletRequest request, HttpServletResponse response)
    {
        PaymentOptionResponse paymentOptionResponse=new PaymentOptionResponse();
        paymentOptionResponse.setResponse("failed");
        try{
            paymentOptionResponse=paymentOptionService.deletePaymentoption(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.ok(paymentOptionResponse);
    }

    @GetMapping("/api/paymentoption/")
    public ResponseEntity<PaymentOptionResponse> getPaymentoptions(HttpServletRequest request, HttpServletResponse response)
    {
        PaymentOptionResponse paymentOptionResponse=new PaymentOptionResponse();
        paymentOptionResponse.setResponse("failed");
        try{
            paymentOptionResponse=paymentOptionService.getPaymentoptions();
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.ok(paymentOptionResponse);
    }

    @GetMapping("/api/paymentoption/status/{status}")
    public ResponseEntity<PaymentOptionResponse> getPaymentoptions(@PathVariable boolean status, HttpServletRequest request, HttpServletResponse response)
    {
        PaymentOptionResponse paymentOptionResponse=new PaymentOptionResponse();
        paymentOptionResponse.setResponse("failed");
        try{
            paymentOptionResponse=paymentOptionService.getPaymentoptions(status);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.ok(paymentOptionResponse);
    }
}
