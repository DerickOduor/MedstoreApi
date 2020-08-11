package com.derick.dto.prescription;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Quote {
    private int id;
    private Double TotalPrice;
    private Date DateSent;
    private boolean Status;
    private Set<Med> medicines = new HashSet<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        TotalPrice = totalPrice;
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

    public Set<Med> getMedicines() {
        return medicines;
    }

    public void setMedicines(Set<Med> medicines) {
        this.medicines = medicines;
    }
}
