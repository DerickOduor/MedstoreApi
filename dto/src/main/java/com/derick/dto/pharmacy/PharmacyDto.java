package com.derick.dto.pharmacy;

import com.derick.domain.Medicine;
import com.derick.domain.UploadPrescription;
import com.derick.dto.county.CountyDto;
import com.derick.dto.medstore.ViewMedicineDto;
import com.derick.dto.prescription.PresciptionDto;

import java.util.Date;
import java.util.Set;

public class PharmacyDto {
    private int id;
    private String Name;
    private String Email;
    private String Phone;
    private String Location;
    private String Latitude;
    private String Longitude;
    private boolean Locked;
    private byte[] Image;
    private boolean Approved;
    private boolean Deleted;
    private boolean Parent;
    private int ParentId;
    private Date DateRegistered;
    private Date DateEdited;
    private Date DateDeleted;
    private CountyDto county;
    private Set<PresciptionDto> uploadPrescriptions;
    private Set<ViewMedicineDto> medicines;

    public Set<ViewMedicineDto> getMedicines() {
        return medicines;
    }

    public void setMedicines(Set<ViewMedicineDto> medicines) {
        this.medicines = medicines;
    }

    public Set<PresciptionDto> getUploadPrescriptions() {
        return uploadPrescriptions;
    }

    public void setUploadPrescriptions(Set<PresciptionDto> uploadPrescriptions) {
        this.uploadPrescriptions = uploadPrescriptions;
    }

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }

    public String getLongitude() {
        return Longitude;
    }

    public void setLongitude(String longitude) {
        Longitude = longitude;
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

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public boolean isLocked() {
        return Locked;
    }

    public void setLocked(boolean locked) {
        Locked = locked;
    }

    public byte[] getImage() {
        return Image;
    }

    public void setImage(byte[] image) {
        Image = image;
    }

    public boolean isApproved() {
        return Approved;
    }

    public void setApproved(boolean approved) {
        Approved = approved;
    }

    public boolean isDeleted() {
        return Deleted;
    }

    public void setDeleted(boolean deleted) {
        Deleted = deleted;
    }

    public boolean isParent() {
        return Parent;
    }

    public void setParent(boolean parent) {
        Parent = parent;
    }

    public int getParentId() {
        return ParentId;
    }

    public void setParentId(int parentId) {
        ParentId = parentId;
    }

    public Date getDateRegistered() {
        return DateRegistered;
    }

    public void setDateRegistered(Date dateRegistered) {
        DateRegistered = dateRegistered;
    }

    public Date getDateEdited() {
        return DateEdited;
    }

    public void setDateEdited(Date dateEdited) {
        DateEdited = dateEdited;
    }

    public Date getDateDeleted() {
        return DateDeleted;
    }

    public void setDateDeleted(Date dateDeleted) {
        DateDeleted = dateDeleted;
    }

    public CountyDto getCounty() {
        return county;
    }

    public void setCounty(CountyDto county) {
        this.county = county;
    }
}
