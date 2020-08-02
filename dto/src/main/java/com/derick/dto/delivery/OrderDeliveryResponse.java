package com.derick.dto.delivery;

import java.util.List;

public class OrderDeliveryResponse {
    private String Response;
    private OrderDeliveryTypeDto deliveryTypeDto;
    private List<OrderDeliveryTypeDto> deliveryTypeDtos;

    public String getResponse() {
        return Response;
    }

    public void setResponse(String response) {
        Response = response;
    }

    public OrderDeliveryTypeDto getDeliveryTypeDto() {
        return deliveryTypeDto;
    }

    public void setDeliveryTypeDto(OrderDeliveryTypeDto deliveryTypeDto) {
        this.deliveryTypeDto = deliveryTypeDto;
    }

    public List<OrderDeliveryTypeDto> getDeliveryTypeDtos() {
        return deliveryTypeDtos;
    }

    public void setDeliveryTypeDtos(List<OrderDeliveryTypeDto> deliveryTypeDtos) {
        this.deliveryTypeDtos = deliveryTypeDtos;
    }
}
