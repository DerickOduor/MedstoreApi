package com.derick.dto.medstore;

import java.util.Date;

public class AddMedicineDto {
    private String Name;

    private Date DateAdded;

    private int Quantity;

    private double Price;

    private boolean Available;

    private byte[] MedicineImage;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Date getDateAdded() {
        return DateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        DateAdded = dateAdded;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public boolean isAvailable() {
        return Available;
    }

    public void setAvailable(boolean available) {
        Available = available;
    }

    public byte[] getMedicineImage() {
        return MedicineImage;
    }

    public void setMedicineImage(byte[] medicineImage) {
        MedicineImage = medicineImage;
    }
}
