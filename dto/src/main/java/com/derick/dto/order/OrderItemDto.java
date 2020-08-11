package com.derick.dto.order;

import com.derick.dto.medstore.ViewMedicineDto;

public class OrderItemDto {
    private int id;
    private ViewMedicineDto medicine;
    //private OrderSlipDto orderSlip;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ViewMedicineDto getMedicine() {
        return medicine;
    }

    public void setMedicine(ViewMedicineDto medicine) {
        this.medicine = medicine;
    }

    /*public OrderSlipDto getOrderSlip() {
        return orderSlip;
    }

    public void setOrderSlip(OrderSlipDto orderSlip) {
        this.orderSlip = orderSlip;
    }*/
}
