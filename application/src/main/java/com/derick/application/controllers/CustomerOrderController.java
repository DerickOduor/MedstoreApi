package com.derick.application.controllers;

import com.derick.application.controllers.util.UserUtil;
import com.derick.dto.chat.PushNotification;
import com.derick.dto.chat.PushNotificationData;
import com.derick.dto.chat.SendNotification;
import com.derick.dto.order.CustomerOrderDto;
import com.derick.dto.order.CustomerOrderResponse;
import com.derick.dto.order.OrderSlipDto;
import com.derick.dto.user.UserDto;
import com.derick.external.firebasemessaging.Fcm;
import com.derick.external.payment.mpesa.c2b.StkPush;
import com.derick.service.ICustomerOrderService;
import com.derick.service.IPharmacyService;
import com.derick.utils.LogFile;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Validated
public class CustomerOrderController {

    @Autowired
    UserUtil userUtil;

    @Autowired
    ICustomerOrderService orderService;

    @Autowired
    IPharmacyService pharmacyService;

    @Autowired
    Fcm fcm;

    @Autowired
    StkPush stkPush;

    @Autowired
    LogFile logFile;

    Gson gson=new Gson();

    @Async("threadPoolTaskExecutor")
    void notifyPharmacy(CustomerOrderDto customerOrderDto){
        try{
            for (OrderSlipDto slip:customerOrderDto.getOrderSlips()){
                try{
                    SendNotification notification=new SendNotification();
                    notification.setTo(slip.getPharmacy().getMobileToken());
                    notification.setCollapse_key("type_a");
                    PushNotification pushNotification=new PushNotification();
                    pushNotification.setTitle("New customer order");
                    pushNotification.setBody("New order placed");
                    notification.setNotification(pushNotification);
                    PushNotificationData pushNotificationData=new PushNotificationData();
                    pushNotificationData.setKey_1("order");
                    pushNotificationData.setKey_2(slip.getId()+"");
                    pushNotificationData.setTitle("New customer order");
                    pushNotificationData.setBody("New order placed");
                    notification.setData(pushNotificationData);

                    logFile.events("SendNotification: "+gson.toJson(notification));

                    fcm.PushNotification(notification);
                }catch (Exception e){
                    e.printStackTrace();
                    logFile.error(e);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            logFile.error(e);
        }
    }

    @PostMapping("/api/order")
    public ResponseEntity<CustomerOrderResponse> addCustomerOrder(@RequestBody CustomerOrderDto orderDto,
                                                                  HttpServletRequest request,
                                                                  HttpServletResponse response)
    {
        try{
           // System.out.println("\n===ORDER===\n"+gson.toJson(orderDto)+"\n===END ORDER===\n");
        }catch (Exception e){
            e.printStackTrace();
            logFile.error(e);
        }
        CustomerOrderResponse orderResponse=new CustomerOrderResponse();
        orderResponse.setResponse("failed");
        try{
            logFile.events("Add Order: "+gson.toJson(orderDto));
            UserDto userDto=userUtil.getUserDetails(request);
            //orderDto.setUser(userDto);
            orderResponse=orderService.addCustomerOrder(orderDto);
            try{
                notifyPharmacy(orderResponse.getCustomerOrderDto());
            }catch (Exception e){
                e.printStackTrace();
                logFile.error(e);
            }
            orderResponse.getCustomerOrderDto().setOrderSlips(null);

        }catch (Exception e){
            e.printStackTrace();
            logFile.error(e);
        }
        return ResponseEntity.ok(orderResponse);
    }

    @PutMapping("/api/order/approve")
    public ResponseEntity<CustomerOrderResponse> approveCustomerOrder(@RequestBody CustomerOrderDto orderDto,
                                                                  HttpServletRequest request,
                                                                  HttpServletResponse response)
    {
        CustomerOrderResponse orderResponse=new CustomerOrderResponse();
        orderResponse.setResponse("failed");
        try{
            orderResponse=orderService.approveCustomerOrder(orderDto);
            try{
                /*for (OrderSlipDto slip:orderResponse.getCustomerOrderDto().getOrderSlips()){
                    try{
                        SendNotification notification=new SendNotification();
                        notification.setTo(slip.getPharmacy().getMobileToken());
                        notification.setCollapse_key("type_a");
                        PushNotification pushNotification=new PushNotification();
                        pushNotification.setTitle("New customer order");
                        pushNotification.setBody("New order placed");
                        notification.setNotification(pushNotification);
                        PushNotificationData pushNotificationData=new PushNotificationData();
                        pushNotificationData.setKey_1("order");
                        pushNotificationData.setKey_2(slip.getId()+"");
                        pushNotificationData.setTitle("New customer order");
                        pushNotificationData.setBody("New order placed");
                        notification.setData(pushNotificationData);

                        fcm.PushNotification(notification);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }*/
            }catch (Exception e){
                e.printStackTrace();
                logFile.error(e);
            }
        }catch (Exception e){
            e.printStackTrace();
            logFile.error(e);
        }
        return ResponseEntity.ok(orderResponse);
    }

    @PutMapping("/api/order/pay/{phone}")
    public ResponseEntity<CustomerOrderResponse> payCustomerOrder(@RequestBody CustomerOrderDto orderDto,
                                                                  @PathVariable(value = "phone") String phone,
                                                                  HttpServletRequest request,
                                                                  HttpServletResponse response)
    {
        CustomerOrderResponse orderResponse=new CustomerOrderResponse();
        orderResponse.setResponse("failed");
        try{
            UserDto userDto=userUtil.getUserDetails(request);
            orderDto.setUser(userDto);

            orderResponse=orderService.payCustomerOrder(orderDto);
            try{
                stkPush.processPayment(orderResponse.getCustomerOrderDto(),phone);
            }catch (Exception e){
                e.printStackTrace();
                logFile.error(e);
                logFile.error(e);
            }
        }catch (Exception e){
            e.printStackTrace();
            logFile.error(e);
            logFile.error(e);
        }
        return ResponseEntity.ok(orderResponse);
    }

    @GetMapping("/api/order/{id}")
    public ResponseEntity<CustomerOrderResponse> getCustomersOrder(@PathVariable int id,
                                                                  HttpServletRequest request,
                                                                  HttpServletResponse response)
    {
        CustomerOrderResponse orderResponse=new CustomerOrderResponse();
        orderResponse.setResponse("failed");
        try{
            orderResponse=orderService.getCustomersOrder(id);
        }catch (Exception e){
            e.printStackTrace();
            logFile.error(e);
        }
        return ResponseEntity.ok(orderResponse);
    }

    @GetMapping("/api/order/orderslip/{id}")
    public ResponseEntity<CustomerOrderResponse> getPharmacyOrderSlips(@PathVariable int id,
                                                                  HttpServletRequest request,
                                                                  HttpServletResponse response)
    {
        CustomerOrderResponse orderResponse=new CustomerOrderResponse();
        orderResponse.setResponse("failed");
        try{
            orderResponse=orderService.getPharmacyOrderSlips(id);
        }catch (Exception e){
            e.printStackTrace();
            logFile.error(e);
        }
        return ResponseEntity.ok(orderResponse);
    }

    @PutMapping("/api/order/orderslip/approve")
    public ResponseEntity<CustomerOrderResponse> approveOrderSlip(@RequestBody OrderSlipDto orderSlipDto,
                                                                  HttpServletRequest request,
                                                                  HttpServletResponse response)
    {
        CustomerOrderResponse orderResponse=new CustomerOrderResponse();
        orderResponse.setResponse("failed");
        try{
            orderResponse=orderService.approvePharmacyOrderSlip(orderSlipDto);
        }catch (Exception e){
            e.printStackTrace();
            logFile.error(e);
        }
        return ResponseEntity.ok(orderResponse);
    }

    @GetMapping("/api/order/orderslip/get/{id}")
    public ResponseEntity<CustomerOrderResponse> getPharmacyOrderSlip(@PathVariable int id,
                                                                  HttpServletRequest request,
                                                                  HttpServletResponse response)
    {
        CustomerOrderResponse orderResponse=new CustomerOrderResponse();
        orderResponse.setResponse("failed");
        try{
            orderResponse=orderService.getPharmacyOrderSlip(id);
        }catch (Exception e){
            e.printStackTrace();
            logFile.error(e);
        }
        return ResponseEntity.ok(orderResponse);
    }

    @DeleteMapping("/api/order/{id}")
    public ResponseEntity<CustomerOrderResponse> deleteCustomerOrder(@PathVariable int id,
                                                                  HttpServletRequest request,
                                                                  HttpServletResponse response)
    {
        CustomerOrderResponse orderResponse=new CustomerOrderResponse();
        orderResponse.setResponse("failed");
        try{
            orderResponse=orderService.deleteCustomerOrder(id);
        }catch (Exception e){
            e.printStackTrace();
            logFile.error(e);
        }
        return ResponseEntity.ok(orderResponse);
    }

    @GetMapping("/api/order/customer/{id}")
    public ResponseEntity<CustomerOrderResponse> getCustomerOrderById(@PathVariable int id,
                                                                  HttpServletRequest request,
                                                                  HttpServletResponse response)
    {
        CustomerOrderResponse orderResponse=new CustomerOrderResponse();
        orderResponse.setResponse("failed");
        try{
            orderResponse=orderService.getCustomerOrderById(id);
        }catch (Exception e){
            e.printStackTrace();
            logFile.error(e);
        }
        return ResponseEntity.ok(orderResponse);
    }
}
