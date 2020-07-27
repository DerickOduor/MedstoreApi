package com.derick.application.controllers;

import com.derick.domain.User;
import com.derick.dto.signupoperations.UserConfirmOtpDto;
import com.derick.dto.signupoperations.UserSignUpDto;
import com.derick.dto.signupoperations.UserSignUpResponse;
import com.derick.service.IUserService;
import com.derick.utils.AppMailer;
import com.derick.utils.RandomGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Date;

@RestController
@Validated
public class UserOperationController {

    @Autowired
    IUserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AppMailer emailSender;

    @Autowired
    RandomGenerator randomGenerator;

    @PostMapping("/api/signup")
    @PreAuthorize("permitAll()")
    public ResponseEntity<?> userSignUp(@RequestBody UserSignUpDto user){
        UserSignUpResponse user1=null;
        try{
            user.setPassword(passwordEncoder.encode(user.getPassword()));

            user1=userService.saveUser(user);

        }catch (Exception e){
            e.printStackTrace();
        }

        return ResponseEntity.ok(user1);
    }

    @PostMapping("/api/confirmOtp")
    @PreAuthorize("permitAll()")
    public ResponseEntity<?> userConfirmOtp(@RequestBody UserConfirmOtpDto user){
       try{
           System.out.println("C");
           return ResponseEntity.ok(userService.confirmOtp(user));
       }catch (Exception e){
           e.printStackTrace();
       }
       return ResponseEntity.ok("failed");
    }

    @PostMapping("/api/resendOtp")
    @PreAuthorize("permitAll()")
    public ResponseEntity<?> userResendOtp(@RequestBody UserConfirmOtpDto user){
       try{
           return ResponseEntity.ok(userService.sendOtp(user));
       }catch (Exception e){
           e.printStackTrace();
       }
       return ResponseEntity.ok("failed");
    }

    @PostMapping("/api/resetpassword")
    @PreAuthorize("permitAll()")
    public ResponseEntity<?> userResetPassword(@RequestBody UserSignUpDto user){
       try{
           return ResponseEntity.ok(userService.resetPassword(user));
       }catch (Exception e){
            e.printStackTrace();
       }
       return ResponseEntity.ok("failed");
    }

}
