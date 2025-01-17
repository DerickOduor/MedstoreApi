package com.derick.application.controllers;

import com.derick.domain.Country;
import com.derick.domain.User;
import com.derick.service.ICountryService;
import com.derick.service.IRoleService;
import com.derick.service.IUserService;
import com.derick.service.implemetation.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("")
@Validated
public class HomeController {

    @Autowired
    ICountryService countryService;

    @Autowired
    IUserService userService;

    @Autowired
    IRoleService roleService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping( value = {"/",""})
    @PreAuthorize("permitAll()")
    public String launch(){
        try {
            roleService.initRoles();
        }catch (Exception e){
            e.printStackTrace();
        }
        return "WELCOME";
    }

    @GetMapping("/home")
    @PreAuthorize("permitAll()")
    public ResponseEntity<String> get()
    {
        try{
            User user=new User();
           // user.setDateRegistered(new Date());
            user.setEmail("oduorderick@gmail.com");
            user.setPhone("254715812661");
            user.setFirstname("Derick");
            user.setLastname("Oduor");
            user.setPassword(passwordEncoder.encode("s3cret"));
            user.setRegistrationConfirmed(true);
            user.setOtpConfirmed(true);

            //userService.saveUser(user);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.ok("KARIBU BLOCK");
    }

    @GetMapping("/countries")
    public List<Country> countries() {
        try{
            return countryService.getAll();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/user")
    public User users() {
        try{
            User user=new User();
           // user.setDateRegistered(new Date());
            user.setEmail("oduorderick@gmail.com");
            user.setPhone("254715812661");
            user.setFirstname("Derick");
            user.setLastname("Oduor");
            user.setPassword(passwordEncoder.encode("s3cret"));
            user.setRegistrationConfirmed(true);
            user.setOtpConfirmed(true);

            //userService.saveUser(user);

            return userService.getUserByEmail("oduorderick@gmail.com");
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
