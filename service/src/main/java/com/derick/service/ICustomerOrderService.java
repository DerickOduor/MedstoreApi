package com.derick.service;

import com.derick.dto.order.CustomerOrderDto;
import com.derick.dto.order.CustomerOrderResponse;

public interface ICustomerOrderService {
    public CustomerOrderResponse getCustomersOrder(int CustomerId);
    public CustomerOrderResponse getCustomerOrderById(int OrderId);
    public CustomerOrderResponse addCustomerOrder(CustomerOrderDto orderDto);
    public CustomerOrderResponse deleteCustomerOrder(int Id);
    public CustomerOrderResponse approveCustomerOrder(CustomerOrderDto orderDto);
    public CustomerOrderResponse payCustomerOrder(CustomerOrderDto orderDto);
}
