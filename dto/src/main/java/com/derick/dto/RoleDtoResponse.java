package com.derick.dto;

import com.derick.dto.userrole.RoleDto;

import java.util.List;

public class RoleDtoResponse {
    private String Response;

    private RoleDto role;

    private List<RoleDto> roleDtos;

    public String getResponse() {
        return Response;
    }

    public void setResponse(String response) {
        Response = response;
    }

    public RoleDto getRole() {
        return role;
    }

    public void setRole(RoleDto role) {
        this.role = role;
    }

    public List<RoleDto> getRoleDtos() {
        return roleDtos;
    }

    public void setRoleDtos(List<RoleDto> roleDtos) {
        this.roleDtos = roleDtos;
    }
}
