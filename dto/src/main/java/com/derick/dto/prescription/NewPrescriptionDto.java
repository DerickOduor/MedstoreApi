package com.derick.dto.prescription;

import com.derick.dto.pharmacy.PharmacyDto;
import com.derick.dto.user.UserDto;

import java.util.Date;

public class NewPrescriptionDto {
    private byte[] file;

    private String Narration;

    private Date DateUploaded;

    private int userId;

    private PharmacyDto pharmacy;

    public PharmacyDto getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(PharmacyDto pharmacy) {
        this.pharmacy = pharmacy;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public String getNarration() {
        return Narration;
    }

    public void setNarration(String narration) {
        Narration = narration;
    }

    public Date getDateUploaded() {
        return DateUploaded;
    }

    public void setDateUploaded(Date dateUploaded) {
        DateUploaded = dateUploaded;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
