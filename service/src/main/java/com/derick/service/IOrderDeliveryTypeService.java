package com.derick.service;

import com.derick.dto.delivery.OrderDeliveryResponse;
import com.derick.dto.delivery.OrderDeliveryTypeDto;

public interface IOrderDeliveryTypeService {
    public OrderDeliveryResponse getDeliveryTypes();
    public OrderDeliveryResponse getDeliveryType(int Id);
    public OrderDeliveryResponse addDeliveryType(OrderDeliveryTypeDto deliveryTypeDto);
    public OrderDeliveryResponse updateDeliveryType(OrderDeliveryTypeDto deliveryTypeDto);
}
