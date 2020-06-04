package com.ftnxml.usermanagement.service;

import com.ftnxml.usermanagement.model.Role;

public interface RoleService {

    boolean addRole(Role newRole);

    boolean removeRole(Long roleId);

    Role getRole(Long roleId);

    Role getRoleByName(String roleName);
}
