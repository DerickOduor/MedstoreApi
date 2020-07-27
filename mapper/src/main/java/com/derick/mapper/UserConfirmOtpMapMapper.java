package com.derick.mapper;

import com.derick.domain.User;
import com.derick.dto.signupoperations.UserConfirmOtpDto;
import com.derick.dto.signupoperations.UserSignUpDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;

@Component
public class UserConfirmOtpMapMapper {

    @Autowired
    ModelMapper modelMapper;

    public UserConfirmOtpDto convertToDto(User user) {
        UserConfirmOtpDto userSignUpDto = modelMapper.map(user, UserConfirmOtpDto.class);
        /*postDto.setSubmissionDate(post.getSubmissionDate(),
                userService.getCurrentUser().getPreference().getTimezone());*/
        return userSignUpDto;
    }

    public User convertToEntity(UserConfirmOtpDto userConfirmOtpDto) throws ParseException {
        User user = modelMapper.map(userConfirmOtpDto, User.class);
        user.setPhone(userConfirmOtpDto.getEmail());
        /*if (userSignUpDto.getId() != 0) {
            Post oldPost = postService.getPostById(postDto.getId());
            user.setRedditID(oldPost.getRedditID());
            user.setSent(oldPost.isSent());
        }*/
        return user;
    }
}
