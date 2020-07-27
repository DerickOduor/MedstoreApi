package com.derick.mapper;

import com.derick.domain.User;
import com.derick.dto.signupoperations.UserSignUpDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;

@Component
public class UserSignUpMapMapper {

    @Autowired
    ModelMapper modelMapper;

    public UserSignUpDto convertToDto(User user) {
        UserSignUpDto userSignUpDto = modelMapper.map(user, UserSignUpDto.class);
        /*postDto.setSubmissionDate(post.getSubmissionDate(),
                userService.getCurrentUser().getPreference().getTimezone());*/
        return userSignUpDto;
    }

    public User convertToEntity(UserSignUpDto userSignUpDto) throws ParseException {
        User user = modelMapper.map(userSignUpDto, User.class);

        /*if (userSignUpDto.getId() != 0) {
            Post oldPost = postService.getPostById(postDto.getId());
            user.setRedditID(oldPost.getRedditID());
            user.setSent(oldPost.isSent());
        }*/
        return user;
    }
}
