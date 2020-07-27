package com.derick.dto.signupoperations;

public  class UserConfirmOtpResponse{
    private UserConfirmOtpDto userSignUpDto;

    private String Response;

    public String getResponse() {
        return Response;
    }

    public void setResponse(String response) {
        Response = response;
    }

    public UserConfirmOtpDto getUserSignUpDto() {
        return userSignUpDto;
    }

    public void setUserSignUpDto(UserConfirmOtpDto userSignUpDto) {
        this.userSignUpDto = userSignUpDto;
    }
}
