package com.derick.dto.quotation;

import com.derick.dto.medstore.ViewMedicineDto;
import com.derick.dto.prescription.PresciptionDto;
import com.derick.dto.user.UserDto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class QuotationDto {
    private int id;
    private PresciptionDto prescription;
    private Set<ViewMedicineDto> medicines = new HashSet<>();
    private Double TotalPrice;
    private Date DateSent;
    private boolean Status;
    private UserDto user;

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PresciptionDto getPrescription() {
        return prescription;
    }

    public void setPrescription(PresciptionDto prescription) {
        this.prescription = prescription;
    }

    public Set<ViewMedicineDto> getMedicines() {
        return medicines;
    }

    public void setMedicines(Set<ViewMedicineDto> medicines) {
        this.medicines = medicines;
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
}
