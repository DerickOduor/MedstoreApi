package com.derick.dto.order;

import com.derick.dto.pharmacy.PharmacyDto;

import java.util.Date;
import java.util.Set;

public class OrderSlipDto {
    private int id;
    private OrderSlipCustomerOrderDto customerOrder;
    private OrderSlipPharmacyDto pharmacy;
    private Set<OrderItemDto> order_slip_items;
    private boolean Approved;
    private Date SlipDate;

    public OrderSlipPharmacyDto getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(OrderSlipPharmacyDto pharmacy) {
        this.pharmacy = pharmacy;
    }

    public void setSlipDate(Date slipDate) {
        SlipDate = slipDate;
    }

    public Date getSlipDate() {
        return SlipDate;
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

    public OrderSlipCustomerOrderDto getCustomerOrder() {
        return customerOrder;
    }

    public void setCustomerOrder(OrderSlipCustomerOrderDto customerOrder) {
        this.customerOrder = customerOrder;
    }

    public Set<OrderItemDto> getOrder_slip_items() {
        return order_slip_items;
    }

    public void setOrder_slip_items(Set<OrderItemDto> order_slip_items) {
        this.order_slip_items = order_slip_items;
    }
}
