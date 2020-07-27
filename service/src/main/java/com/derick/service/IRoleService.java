package com.derick.service;

import com.derick.domain.Role;
import com.derick.dto.RoleDtoResponse;
import com.derick.dto.userrole.RoleDto;

public interface IRoleService {

    public RoleDtoResponse getRole(int id) throws Exception;
    public Role getRole(String name) throws Exception;
    public RoleDtoResponse getRole() throws Exception;
    public RoleDtoResponse initRoles() throws Exception;
    public RoleDtoResponse addRole(RoleDto role) throws Exception;

}
