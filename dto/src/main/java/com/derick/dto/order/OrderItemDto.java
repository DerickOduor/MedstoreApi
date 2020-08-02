package com.derick.dto.order;

import com.derick.dto.medstore.ViewMedicineDto;

public class OrderItemDto {
    private int id;
    private ViewMedicineDto medicine;
    private CustomerOrderDto order;

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

    public CustomerOrderDto getOrder() {
        return order;
    }

    public void setOrder(CustomerOrderDto order) {
        this.order = order;
    }
}
