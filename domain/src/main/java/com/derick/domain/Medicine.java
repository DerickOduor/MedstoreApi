package com.derick.domain;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity(name = "Medicine")
@Table(name = "Medicine")
public class Medicine implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    @NotNull
    private String Name;

    @Column(nullable = false)
    @NotNull
    private String Description;

    @Column(nullable = true)
    @NotNull
    private String SideEffects;

    @Column(nullable = true)
    @NotNull
    private String OtherDetails;

    @Column(nullable = false)
    @NotNull
    private String Dosage;

    @Column(nullable = false)
    @NotNull
    private Date DateAdded;

    @Column(nullable = false)
    @NotNull
    private int Quantity;

    @Column(nullable = false)
    @NotNull
    private double Price;

    @Column(nullable = false,columnDefinition = "double default 0")
    @NotNull
    private double Discount;

    @Column(nullable = false)
    @NotNull
    private double PriceAfterDiscount;

    @Column
    @NotNull
    private boolean Available;

    @Column(nullable = false)
    @Lob
    private byte[] MedicineImage;

    @OneToMany(mappedBy = "medicine", fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST)
    private Set<OrderItem> orderItems;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pharmacy_id", referencedColumnName = "id", nullable = false)
    private Pharmacy pharmacy;

    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }

    public Set<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Set<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getSideEffects() {
        return SideEffects;
    }

    public void setSideEffects(String sideEffects) {
        SideEffects = sideEffects;
    }

    public String getOtherDetails() {
        return OtherDetails;
    }

    public void setOtherDetails(String otherDetails) {
        OtherDetails = otherDetails;
    }

    public String getDosage() {
        return Dosage;
    }

    public void setDosage(String dosage) {
        Dosage = dosage;
    }

    public double getDiscount() {
        return Discount;
    }

    public void setDiscount(double discount) {
        Discount = discount;
    }

    public double getPriceAfterDiscount() {
        return PriceAfterDiscount;
    }

    public void setPriceAfterDiscount(double priceAfterDiscount) {
        try{
            PriceAfterDiscount=(((100-this.getDiscount())*this.getPrice())/100);
        }catch (Exception e){
            PriceAfterDiscount = priceAfterDiscount;
            e.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
