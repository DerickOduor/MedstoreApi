package com.derick.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity(name = "CustomerOrder")
@Table(name = "CustomerOrder")
public class CustomerOrder implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String OrderNumber;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id", referencedColumnName = "id", nullable = false)
    private User user;

    @Column(nullable = false)
    private Date DateOrdered;

    @Column(nullable = false)
    private boolean Paid;

    @Column(nullable = false)
    private boolean Approved;

    @Column(nullable = false)
    private double Price;

    @Column(nullable = false)
    private double TotalPrice;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "delivery_type_id", referencedColumnName = "id", nullable = false)
    private OrderDeliveryType DeliveryType;


    @OneToMany(mappedBy = "customerOrder",fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
    private Set<OrderSlip> orderSlips;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_information_id", referencedColumnName = "id")
    private OrderDeliveryInformation deliveryInformation;

    @OneToOne(mappedBy = "order")
    private StkPushRequest stkPushRequest;

    public Set<OrderSlip> getOrderSlips() {
        return orderSlips;
    }

    public void setOrderSlips(Set<OrderSlip> orderSlips) {
        this.orderSlips = orderSlips;
    }

    public StkPushRequest getStkPushRequest() {
        return stkPushRequest;
    }

    public void setStkPushRequest(StkPushRequest stkPushRequest) {
        this.stkPushRequest = stkPushRequest;
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

    public OrderDeliveryInformation getDeliveryInformation() {
        return deliveryInformation;
    }

    public void setDeliveryInformation(OrderDeliveryInformation deliveryInformation) {
        this.deliveryInformation = deliveryInformation;
    }



    /*public Set<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Set<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }*/

    @Transient
    public double getTotalCost(){
        double cost=0.0;
        try {
            cost=this.Price*this.DeliveryType.getFee();
        }catch (Exception e){
            e.printStackTrace();
        }
        return cost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
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

    public OrderDeliveryType getDeliveryType() {
        return DeliveryType;
    }

    public void setDeliveryType(OrderDeliveryType deliveryType) {
        DeliveryType = deliveryType;
    }
}
