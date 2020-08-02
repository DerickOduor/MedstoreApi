package com.derick.dto.order;


public class DeliveryInformationDto {
    private int id;
    private CustomerOrderDto order;
    private String Phone;
    private String FullName;
    private String Address;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CustomerOrderDto getOrder() {
        return order;
    }

    public void setOrder(CustomerOrderDto order) {
        this.order = order;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }
}
