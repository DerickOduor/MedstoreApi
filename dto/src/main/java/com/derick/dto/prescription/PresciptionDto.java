package com.derick.dto.prescription;

import com.derick.dto.pharmacy.PharmacyDto;
import com.derick.dto.quotation.QuotationDto;
import com.derick.dto.user.UserDto;

import javax.persistence.*;
import java.util.Date;

public class PresciptionDto {

    private int id;

    private byte[] file;

    private String Narration;

    private Date DateUploaded;

    private UserDto userDto;

    private PharmacyDto pharmacy;
    private Quote prescriptionQuotation;

    public Quote getPrescriptionQuotation() {
        return prescriptionQuotation;
    }

    public void setPrescriptionQuotation(Quote prescriptionQuotation) {
        this.prescriptionQuotation = prescriptionQuotation;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public PharmacyDto getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(PharmacyDto pharmacy) {
        this.pharmacy = pharmacy;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public UserDto getUser() {
        return userDto;
    }

    public void setUser(UserDto userDto) {
        this.userDto = userDto;
    }
}
