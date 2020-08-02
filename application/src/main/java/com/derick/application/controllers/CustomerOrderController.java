package com.derick.application.controllers;

import com.derick.application.controllers.util.UserUtil;
import com.derick.dto.order.CustomerOrderDto;
import com.derick.dto.order.CustomerOrderResponse;
import com.derick.dto.user.UserDto;
import com.derick.service.ICustomerOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/api/order/")
    public ResponseEntity<CustomerOrderResponse> addCustomerOrder(@RequestBody CustomerOrderDto orderDto,
                                                                  HttpServletRequest request,
                                                                  HttpServletResponse response)
    {
        CustomerOrderResponse orderResponse=new CustomerOrderResponse();
        orderResponse.setResponse("failed");
        try{
            UserDto userDto=userUtil.getUserDetails(request);
            orderDto.setUser(userDto);

            orderResponse=orderService.addCustomerOrder(orderDto);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.ok(orderResponse);
    }

    @PutMapping("/api/order/approve/")
    public ResponseEntity<CustomerOrderResponse> approveCustomerOrder(@RequestBody CustomerOrderDto orderDto,
                                                                  HttpServletRequest request,
                                                                  HttpServletResponse response)
    {
        CustomerOrderResponse orderResponse=new CustomerOrderResponse();
        orderResponse.setResponse("failed");
        try{
            orderResponse=orderService.approveCustomerOrder(orderDto);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.ok(orderResponse);
    }

    @PutMapping("/api/order/pay/")
    public ResponseEntity<CustomerOrderResponse> payCustomerOrder(@RequestBody CustomerOrderDto orderDto,
                                                                  HttpServletRequest request,
                                                                  HttpServletResponse response)
    {
        CustomerOrderResponse orderResponse=new CustomerOrderResponse();
        orderResponse.setResponse("failed");
        try{
            UserDto userDto=userUtil.getUserDetails(request);
            orderDto.setUser(userDto);

            orderResponse=orderService.payCustomerOrder(orderDto);
        }catch (Exception e){
            e.printStackTrace();
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
        }
        return ResponseEntity.ok(orderResponse);
    }
}
