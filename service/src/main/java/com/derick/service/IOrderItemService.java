package com.derick.service;

import com.derick.dto.order.OrderItemResponse;

public interface IOrderItemService {
    public OrderItemResponse getItems(int OrderId);
    public OrderItemResponse getItem(int id);
}
