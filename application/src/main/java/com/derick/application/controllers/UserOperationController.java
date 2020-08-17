package com.derick.application.controllers;

import com.derick.domain.User;
import com.derick.dto.chat.SendNotification;
import com.derick.dto.signupoperations.UserConfirmOtpDto;
import com.derick.dto.signupoperations.UserSignUpDto;
import com.derick.dto.signupoperations.UserSignUpResponse;
import com.derick.dto.user.UserDto;
import com.derick.external.firebasemessaging.Fcm;
import com.derick.service.IUserService;
import com.derick.utils.AppMailer;
import com.derick.utils.LogFile;
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
import org.springframework.web.bind.annotation.*;

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
    Fcm fcm;

    @Autowired
    RandomGenerator randomGenerator;

    @Autowired
    LogFile logFile;

    @PostMapping("/api/signup")
    @PreAuthorize("permitAll()")
    public ResponseEntity<?> userSignUp(@RequestBody UserSignUpDto user){
        UserSignUpResponse user1=null;
        try{
            user.setPassword(passwordEncoder.encode(user.getPassword()));

            user1=userService.saveUser(user);

        }catch (Exception e){
            logFile.error(e);
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
           logFile.error(e);
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
           logFile.error(e);
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

    @PostMapping("/api/sendpushnotification")
    public ResponseEntity<?> sendNotification(@RequestBody SendNotification sendNotification){
       try{
           fcm.PushNotification(sendNotification);
           return ResponseEntity.ok("SENT");
       }catch (Exception e){
            e.printStackTrace();
           logFile.error(e);
       }
       return ResponseEntity.ok("failed");
    }

    @PutMapping("/api/mobiletoken")
    public ResponseEntity<?> updateMobileToken(@RequestBody UserDto user){
       try{
           return ResponseEntity.ok(userService.updateMobileToken(user));
       }catch (Exception e){
           logFile.error(e);
            e.printStackTrace();
       }
       return ResponseEntity.ok("failed");
    }

    @PutMapping("/api/user")
    public ResponseEntity<?> updateUser(@RequestBody UserDto user){
       try{
           return ResponseEntity.ok(userService.updateUser(user));
       }catch (Exception e){
           logFile.error(e);
            e.printStackTrace();
       }
       return ResponseEntity.ok("failed");
    }

}
