package com.derick.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "OrderItem")
@Table(name = "OrderItem")
public class OrderItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false,cascade = CascadeType.ALL)
    @JoinColumn(name = "order_slip_id", referencedColumnName = "id", nullable = false)
    private OrderSlip orderSlip;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "medicine_id", referencedColumnName = "id", nullable = false)
    private Medicine medicine;

    @Transient
    public double getItemPrice(){
        double price=0.0;
        try{
            price=(((100-this.getMedicine().getDiscount())*this.getMedicine().getPrice())/100);
        }catch (Exception e){
            e.printStackTrace();
        }
        return price;
    }

    public OrderSlip getOrderSlip() {
        return orderSlip;
    }

    public void setOrderSlip(OrderSlip orderSlip) {
        this.orderSlip = orderSlip;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /*public CustomerOrder getOrder() {
        return order;
    }

    public void setOrder(CustomerOrder order) {
        this.order = order;
    }*/

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }
}
