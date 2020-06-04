package com.ftnxml.usermanagement.service;

import java.util.List;
import java.util.Set;

import com.ftnxml.usermanagement.enums.AccountStatus;
import com.ftnxml.usermanagement.model.Role;
import com.ftnxml.usermanagement.model.User;

public interface UserService {

    boolean registerNewUser(User newUser);

    User getUser(Long id);

    List<User> getAllUsers();

    boolean removeUser(Long userId);

    boolean changeAccountStatus(Long userId, AccountStatus newStatus);

    boolean addRole(Long userId, Long roleId);

    boolean removeRole(Long userId, Long roleId);

    Set<Role> getUserRoles(Long userId);

}
