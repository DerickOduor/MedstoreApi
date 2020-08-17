package com.derick.service;

import com.derick.dto.order.CustomerOrderDto;
import com.derick.dto.order.CustomerOrderResponse;
import com.derick.dto.order.OrderSlipDto;

public interface ICustomerOrderService {
    public CustomerOrderResponse getCustomersOrder(int CustomerId);
    public CustomerOrderResponse getPharmacyOrderSlips(int PharmacyId);
    public CustomerOrderResponse getPharmacyOrderSlip(int OrderSlipId);
    public CustomerOrderResponse approvePharmacyOrderSlip(OrderSlipDto orderSlipDto);
    public CustomerOrderResponse getCustomerOrderById(int OrderId);
    public CustomerOrderResponse addCustomerOrder(CustomerOrderDto orderDto);
    public CustomerOrderResponse deleteCustomerOrder(int Id);
    public CustomerOrderResponse approveCustomerOrder(CustomerOrderDto orderDto);
    public CustomerOrderResponse payCustomerOrder(CustomerOrderDto orderDto);
}
