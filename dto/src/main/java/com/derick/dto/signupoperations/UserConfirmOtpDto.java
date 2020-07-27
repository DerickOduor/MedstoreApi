package com.derick.dto.signupoperations;

public class UserConfirmOtpDto {
    private String Email;
    private String Otp;

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getOtp() {
        return Otp;
    }

    public void setOtp(String otp) {
        Otp = otp;
    }
}
