package com.derick.service;

import com.derick.domain.User;
import com.derick.dto.signinoperations.UserSigninResponse;
import com.derick.dto.signupoperations.UserConfirmOtpDto;
import com.derick.dto.signupoperations.UserConfirmOtpResponse;
import com.derick.dto.signupoperations.UserSignUpDto;
import com.derick.dto.signupoperations.UserSignUpResponse;
import com.derick.dto.user.UserDto;
import javassist.NotFoundException;

public interface IUserService {

    public User getUser(String username) throws NotFoundException;
    public User getUser(int id) throws NotFoundException;
    public User getUserByPhone(String phone) throws NotFoundException;
    public User getUserByEmail(String email) throws NotFoundException;
    public UserSignUpResponse saveUser(UserSignUpDto user) throws Exception;
    public UserSigninResponse updateMobileToken(UserDto user) throws Exception;
    public UserSignUpResponse resetPassword(UserSignUpDto user) throws Exception;
    public UserConfirmOtpResponse confirmOtp(UserConfirmOtpDto user) throws Exception;
    public UserConfirmOtpResponse sendOtp(UserConfirmOtpDto user) throws Exception;

}
