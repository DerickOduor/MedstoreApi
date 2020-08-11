package com.derick.dto.signinoperations;

import com.derick.domain.JwtResponse;
import com.derick.dto.user.UserDto;

public class UserSigninResponse {
    public String Response;
    public String Token;
    public JwtResponse jwtResponse;
    public UserDto UserDto;
    private String MobileToken;

    public String getMobileToken() {
        return MobileToken;
    }

    public void setMobileToken(String mobileToken) {
        MobileToken = mobileToken;
    }

    public com.derick.dto.user.UserDto getUserDto() {
        return UserDto;
    }

    public void setUserDto(com.derick.dto.user.UserDto userDto) {
        UserDto = userDto;
    }

    public String getResponse() {
        return Response;
    }

    public void setResponse(String response) {
        Response = response;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }

    public JwtResponse getJwtResponse() {
        return jwtResponse;
    }

    public void setJwtResponse(JwtResponse jwtResponse) {
        this.jwtResponse = jwtResponse;
    }
}
