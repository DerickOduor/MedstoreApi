package com.derick.application.controllers.util;

import com.derick.application.configuration.JwtTokenUtil;
import com.derick.dto.user.UserDto;
import com.derick.mapper.user.UserMapper;
import com.derick.service.IUserService;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class UserUtil {

    @Autowired
    IUserService userService;

    @Autowired
    UserMapper userMapper;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public UserDto getUserDetails(HttpServletRequest request){
        UserDto user=null;
        String username = "";
        String jwtToken = "";
        try{
            final String requestTokenHeader = request.getHeader("Authorization");
            if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
                jwtToken = requestTokenHeader.substring(7);
                try {
                    username = jwtTokenUtil.getUsernameFromToken(jwtToken);
                } catch (IllegalArgumentException e) {
                    System.out.println("Unable to get JWT Token");
                } catch (ExpiredJwtException e) {
                    System.out.println("JWT Token has expired");
                }
            } else {
                System.out.println("JWT Token does not begin with Bearer String");
            }

            user=userMapper.convertToDto(userService.getUserByEmail(username));

            if(user==null){
                user=userMapper.convertToDto(userService.getUserByPhone(username));
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }

    public UserDto getUserDetails(String token)
    {
        UserDto user=null;
        String username = "";
        String jwtToken = "";
        try{
            final String requestTokenHeader = "Bearer "+token;
            if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
                jwtToken = requestTokenHeader.substring(7);
                try {
                    username = jwtTokenUtil.getUsernameFromToken(jwtToken);
                } catch (IllegalArgumentException e) {
                    System.out.println("Unable to get JWT Token");
                } catch (ExpiredJwtException e) {
                    System.out.println("JWT Token has expired");
                }
            } else {
                System.out.println("JWT Token does not begin with Bearer String");
            }

            user=userMapper.convertToDto(userService.getUserByEmail(username));

            if(user==null){
                user=userMapper.convertToDto(userService.getUserByPhone(username));
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }


}
