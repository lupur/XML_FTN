package com.ftnxml.usermanagement.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.ftnxml.usermanagement.model.Role;
import com.ftnxml.usermanagement.repository.RoleRepository;

public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public boolean addRole(Role newRole) {
        roleRepository.save(newRole);
        return false;
    }

    @Override
    public boolean removeRole(Long roleId) {
        try {
            Role role = roleRepository.findById(roleId).get();
            roleRepository.delete(role);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Role getRole(Long roleId) {
        try {
            Role role = roleRepository.findById(roleId).get();
            return role;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Role getRoleByName(String roleName) {
        try {
            Role role = roleRepository.findByName(roleName);
            return role;
        } catch (Exception e) {
            return null;
        }
    }

}
