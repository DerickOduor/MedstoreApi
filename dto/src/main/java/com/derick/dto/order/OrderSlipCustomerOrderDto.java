package com.derick.dto.order;

import com.derick.dto.user.UserDto;

import java.util.Date;

public class OrderSlipCustomerOrderDto {
    private int id;
    private String OrderNumber;
    private UserDto user;
    private Date DateOrdered;
    private boolean Approved;
    private boolean Paid;
    private double Price;
    private double TotalPrice;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return OrderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        OrderNumber = orderNumber;
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

    public boolean isPaid() {
        return Paid;
    }

    public void setPaid(boolean paid) {
        Paid = paid;
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
}
