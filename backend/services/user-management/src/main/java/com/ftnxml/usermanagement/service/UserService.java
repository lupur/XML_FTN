package com.ftnxml.usermanagement.service;

import java.util.List;

import com.ftnxml.usermanagement.enums.AccountStatus;
import com.ftnxml.usermanagement.model.User;

public interface UserService {

    boolean RegisterNewUser(User newUser);

    User getUser(Long id);

    List<User> getAllUsers();

    boolean RemoveUser(Long userId);

    boolean ChangeAccountStatus(Long userId, AccountStatus newStatus);

    boolean ChangeRole(Long userId, String roleName);
}
