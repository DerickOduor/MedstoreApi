package com.derick.mapper.roles;

import com.derick.domain.Medicine;
import com.derick.domain.Role;
import com.derick.dto.userrole.RoleDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Component
public class RoleMapper {

    @Autowired
    ModelMapper modelMapper;

    public RoleDto convertToDto(Role role) throws Exception{
        RoleDto roleDto = modelMapper.map(role, RoleDto.class);
        return roleDto;
    }

    public List<RoleDto> convertToDto(List<Role> roles) throws Exception{
        List<RoleDto> roleDtos=new ArrayList<>();
        modelMapper.map(roles, roleDtos);
        return roleDtos;
    }

    public Role convertToEntity(RoleDto roleDto) throws ParseException {
        Role role = modelMapper.map(roleDto, Role.class);
        return role;
    }

}
