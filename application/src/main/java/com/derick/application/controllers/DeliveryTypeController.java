package com.derick.application.controllers;

import com.derick.application.controllers.util.UserUtil;
import com.derick.dto.delivery.OrderDeliveryResponse;
import com.derick.dto.delivery.OrderDeliveryTypeDto;
import com.derick.service.IOrderDeliveryTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Validated
public class DeliveryTypeController {

    @Autowired
    UserUtil userUtil;

    @Autowired
    IOrderDeliveryTypeService deliveryTypeService;

    @PostMapping("/api/deliverytype/type")
    public ResponseEntity<OrderDeliveryResponse> addDeliveryType(@RequestBody OrderDeliveryTypeDto deliveryTypeDto, HttpServletRequest request, HttpServletResponse response)
    {
        OrderDeliveryResponse deliveryResponse=new OrderDeliveryResponse();
        deliveryResponse.setResponse("failed");
        try {
            deliveryResponse=deliveryTypeService.addDeliveryType(deliveryTypeDto);
            return ResponseEntity.ok(deliveryResponse);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.ok(deliveryResponse);
    }

    @GetMapping("/api/deliverytype")
    public ResponseEntity<OrderDeliveryResponse> getDeliveryTypes(HttpServletRequest request, HttpServletResponse response)
    {
        OrderDeliveryResponse deliveryResponse=new OrderDeliveryResponse();
        deliveryResponse.setResponse("failed");
        try {
            deliveryResponse=deliveryTypeService.getDeliveryTypes();
            return ResponseEntity.ok(deliveryResponse);
        }catch (Exception e){
            e.printStackTrace();
        }

        return ResponseEntity.ok(deliveryResponse);
    }

    @GetMapping("/api/deliverytype/{id}")
    public ResponseEntity<OrderDeliveryResponse> getDeliveryType(@PathVariable int id, HttpServletRequest request, HttpServletResponse response)
    {
        OrderDeliveryResponse deliveryResponse=new OrderDeliveryResponse();
        deliveryResponse.setResponse("failed");
        try {
            deliveryResponse=deliveryTypeService.getDeliveryType(id);
            return ResponseEntity.ok(deliveryResponse);
        }catch (Exception e){
            e.printStackTrace();
        }

        return ResponseEntity.ok(deliveryResponse);
    }

    @PutMapping("/api/deliverytype/type")
    public ResponseEntity<OrderDeliveryResponse> updateDeliveryType(@RequestBody OrderDeliveryTypeDto deliveryTypeDto, HttpServletRequest request, HttpServletResponse response)
    {
        OrderDeliveryResponse deliveryResponse=new OrderDeliveryResponse();
        deliveryResponse.setResponse("failed");
        try {
            deliveryResponse=deliveryTypeService.updateDeliveryType(deliveryTypeDto);
            return ResponseEntity.ok(deliveryResponse);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.ok(deliveryResponse);
    }

}
