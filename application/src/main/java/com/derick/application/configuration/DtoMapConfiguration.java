package com.derick.application.configuration;

import com.derick.domain.User;
import com.derick.dto.user.UserDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DtoMapConfiguration {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper=new ModelMapper();
        /*PropertyMap<UserDto, User> propertyMap = new PropertyMap<UserDto, User> (){
            protected void configure() {
                map(source.getOtpDate()).setOtpDate(null);
                map(source.).setOtpDate(null);
                map(source.isOtpConfirmed()).setOtpConfirmed(true);
            }
        };
        modelMapper.addMappings(propertyMap);*/
        return modelMapper;//new ModelMapper();
    }
}
