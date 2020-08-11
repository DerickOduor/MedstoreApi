package com.derick.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity(name = "OrderSlip")
@Table(name = "OrderSlip")
public class OrderSlip implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false,cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_order_id", referencedColumnName = "id", nullable = false)
    private CustomerOrder customerOrder;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pharmacy_id", referencedColumnName = "id", nullable = false)
    private Pharmacy pharmacy;

    @OneToMany(mappedBy = "orderSlip", fetch = FetchType.LAZY,
            cascade = CascadeType.REMOVE)
    private Set<OrderItem> order_slip_items;

    @Column
    private boolean Approved;

    @Column
    private Date SlipDate;

    public Date getSlipDate() {
        return SlipDate;
    }

    public void setSlipDate(Date slipDate) {
        SlipDate = slipDate;
    }

    public boolean isApproved() {
        return Approved;
    }

    public void setApproved(boolean approved) {
        Approved = approved;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CustomerOrder getCustomerOrder() {
        return customerOrder;
    }

    public void setCustomerOrder(CustomerOrder customerOrder) {
        this.customerOrder = customerOrder;
    }

    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }

    public Set<OrderItem> getOrder_slip_items() {
        return order_slip_items;
    }

    public void setOrder_slip_items(Set<OrderItem> order_slip_items) {
        this.order_slip_items = order_slip_items;
    }
}
