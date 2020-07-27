package com.derick.dto.signupoperations;

public class UserSignUpResponse {

    private UserSignUpDto userSignUpDto;

    private String Response;

    public String getResponse() {
        return Response;
    }

    public void setResponse(String response) {
        Response = response;
    }

    public UserSignUpDto getUserSignUpDto() {
        return userSignUpDto;
    }

    public void setUserSignUpDto(UserSignUpDto userSignUpDto) {
        this.userSignUpDto = userSignUpDto;
    }

}
