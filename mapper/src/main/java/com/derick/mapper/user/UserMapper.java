package com.derick.mapper.user;

import com.derick.domain.User;
import com.derick.dto.user.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapper {
    @Autowired
    ModelMapper modelMapper;

    public UserDto convertToDto(User user) throws Exception{
        UserDto userDto = modelMapper.map(user, UserDto.class);
        return userDto;
    }

    public List<UserDto> convertToDto(List<User> user) throws Exception{
        List<UserDto> userDto =new ArrayList<>();
        for(User u:user){
            try{
                userDto.add(convertToDto(u));
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return userDto;
    }

    public User convertToEntity(UserDto userDto) throws ParseException {
        User user = modelMapper.map(userDto, User.class);
        return user;
    }
}
