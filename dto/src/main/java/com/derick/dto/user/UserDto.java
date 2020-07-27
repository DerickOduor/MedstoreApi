package com.derick.dto.user;

import java.util.Date;

public class UserDto {
    private int id;

    private String Firstname;

    private String Lastname;

    private String Email;

    private String Phone;

    private Date DateRegistered;

    private boolean RegistrationConfirmed;

    private boolean Locked;

    private boolean OtpConfirmed;

    private Date OtpDate;

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public Date getDateRegistered() {
        return DateRegistered;
    }

    public void setDateRegistered(Date dateRegistered) {
        DateRegistered = dateRegistered;
    }

    public boolean isRegistrationConfirmed() {
        return RegistrationConfirmed;
    }

    public void setRegistrationConfirmed(boolean registrationConfirmed) {
        RegistrationConfirmed = registrationConfirmed;
    }

    public boolean isLocked() {
        return Locked;
    }

    public void setLocked(boolean locked) {
        Locked = locked;
    }

    public boolean isOtpConfirmed() {
        return OtpConfirmed;
    }

    public void setOtpConfirmed(boolean otpConfirmed) {
        OtpConfirmed = otpConfirmed;
    }

    public Date getOtpDate() {
        return OtpDate;
    }

    public void setOtpDate(Date otpDate) {
        OtpDate = otpDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return Firstname;
    }

    public void setFirstname(String firstname) {
        Firstname = firstname;
    }

    public String getLastname() {
        return Lastname;
    }

    public void setLastname(String lastname) {
        Lastname = lastname;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
