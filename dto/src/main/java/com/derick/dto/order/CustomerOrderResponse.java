package com.derick.dto.order;

import java.util.List;

public class CustomerOrderResponse {
    private String Response;
    private CustomerOrderDto customerOrderDto;
    private List<CustomerOrderDto> customerOrderDtos;

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
