package com.derick.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity(name = "PrescriptionQuotation")
@Table(name = "PrescriptionQuotation")
public class PrescriptionQuotation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "upload_prescription_id", referencedColumnName = "id")
    private UploadPrescription prescription;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id", referencedColumnName = "id", nullable = false)
    private User user;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "quotation_medicine",
            joinColumns = @JoinColumn(name = "quotation_id"),
            inverseJoinColumns = @JoinColumn(name = "medicine_id")
    )
    private Set<Medicine> medicines = new HashSet<>();

    private double calculateTotalPrice(){
        double totalPrice=0.0;
        for(Medicine m: medicines){
            totalPrice+=m.getPriceAfterDiscount();
        }
        return totalPrice;
    }

    @Column(nullable = false)
    private Double TotalPrice=calculateTotalPrice();

    @Column(nullable = false)
    private Date DateSent;

    @Column
    private boolean Status;

    @Column
    private boolean Sent;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isSent() {
        return Sent;
    }

    public void setSent(boolean sent) {
        Sent = sent;
    }

    public Date getDateSent() {
        return DateSent;
    }

    public void setDateSent(Date dateSent) {
        DateSent = dateSent;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean status) {
        Status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UploadPrescription getPrescription() {
        return prescription;
    }

    public void setPrescription(UploadPrescription prescription) {
        this.prescription = prescription;
    }

    public Set<Medicine> getMedicines() {
        return medicines;
    }

    public void setMedicines(Set<Medicine> medicines) {
        this.medicines = medicines;
    }

    public Double getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        try{
            TotalPrice=0.0;
            for(Medicine m: medicines){
                TotalPrice+=m.getPriceAfterDiscount();
            }
        }catch (Exception e){
            TotalPrice = totalPrice;
            e.printStackTrace();
        }
    }
}
