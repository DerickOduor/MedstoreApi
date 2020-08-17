package com.derick.dto.order;

import java.util.List;

public class CustomerOrderResponse {
    private String Response;
    private CustomerOrderDto customerOrderDto;
    private List<CustomerOrderDto> customerOrderDtos;
    private OrderSlipDto orderSlipDto;
    private List<OrderSlipDto> orderSlipDtos;

    public OrderSlipDto getOrderSlipDto() {
        return orderSlipDto;
    }

    public void setOrderSlipDto(OrderSlipDto orderSlipDto) {
        this.orderSlipDto = orderSlipDto;
    }

    public List<OrderSlipDto> getOrderSlipDtos() {
        return orderSlipDtos;
    }

    public void setOrderSlipDtos(List<OrderSlipDto> orderSlipDtos) {
        this.orderSlipDtos = orderSlipDtos;
    }

    public String getResponse() {
        return Response;
    }

    public void setResponse(String response) {
        Response = response;
    }

    public CustomerOrderDto getCustomerOrderDto() {
        return customerOrderDto;
    }

    public void setCustomerOrderDto(CustomerOrderDto customerOrderDto) {
        this.customerOrderDto = customerOrderDto;
    }

    public List<CustomerOrderDto> getCustomerOrderDtos() {
        return customerOrderDtos;
    }

    public void setCustomerOrderDtos(List<CustomerOrderDto> customerOrderDtos) {
        this.customerOrderDtos = customerOrderDtos;
    }
}
