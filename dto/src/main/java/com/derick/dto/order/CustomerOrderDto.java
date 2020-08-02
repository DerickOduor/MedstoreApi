package com.derick.dto.order;

import com.derick.dto.delivery.OrderDeliveryTypeDto;
import com.derick.dto.user.UserDto;

import java.util.Date;
import java.util.Set;

public class CustomerOrderDto {
    private int id;
    private String OrderNumber;
    private UserDto user;
    private Date DateOrdered;
    private boolean Approved;
    private boolean Paid;
    private double Price;
    private double TotalPrice;
    private Set<OrderItemDto> orderItems;
    private OrderDeliveryTypeDto DeliveryType;
    private DeliveryInformationDto deliveryInformation;

    public DeliveryInformationDto getDeliveryInformation() {
        return deliveryInformation;
    }

    public void setDeliveryInformation(DeliveryInformationDto deliveryInformation) {
        this.deliveryInformation = deliveryInformation;
    }

    public String getOrderNumber() {
        return OrderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        OrderNumber = orderNumber;
    }

    public boolean isPaid() {
        return Paid;
    }

    public void setPaid(boolean paid) {
        Paid = paid;
    }

    public OrderDeliveryTypeDto getDeliveryType() {
        return DeliveryType;
    }

    public void setDeliveryType(OrderDeliveryTypeDto deliveryType) {
        DeliveryType = deliveryType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public Date getDateOrdered() {
        return DateOrdered;
    }

    public void setDateOrdered(Date dateOrdered) {
        DateOrdered = dateOrdered;
    }

    public boolean isApproved() {
        return Approved;
    }

    public void setApproved(boolean approved) {
        Approved = approved;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public double getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        TotalPrice = totalPrice;
    }

    public Set<OrderItemDto> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Set<OrderItemDto> orderItems) {
        this.orderItems = orderItems;
    }
}
