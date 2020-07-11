package com.ftnxml.usermanagement.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ftnxml.usermanagement.enums.AccountStatus;
import com.ftnxml.usermanagement.model.Role;
import com.ftnxml.usermanagement.model.User;
import com.ftnxml.usermanagement.repository.RoleRepository;
import com.ftnxml.usermanagement.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    private final String USER_ROLE_NAME = "USER";
    private final String AGENT_ROLE_NAME = "AGENT";

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public Long registerNewAgent(User newAgent) {
        if (userRepository.findByUsername(newAgent.getUsername()) != null) {
            return -1l;
        }
        Role role = roleRepository.findByName(AGENT_ROLE_NAME);
        if (role == null)
            return -1l;
        newAgent.addRole(role);
        newAgent.setPassword(encoder.encode(newAgent.getPassword()));

        User u = userRepository.save(newAgent);
        return u.getId();
    }

    @Override
    public boolean registerNewUser(User newUser) {
        if (userRepository.findByUsername(newUser.getUsername()) != null) {
            return false;
        }
        Role role = roleRepository.findByName(USER_ROLE_NAME);
        if (role == null)
            return false;
        newUser.addRole(role);
        newUser.setPassword(encoder.encode(newUser.getPassword()));

        userRepository.save(newUser);
        return true;
    }

    @Override
    public User getUser(Long id) {
        if (id == null) {
            return null;
        }
        Optional<User> u = userRepository.findById(id);
        if (u.isPresent()) {
            return u.get();
        }
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public boolean removeUser(Long userId) {
        User u = getUser(userId);
        if (u == null) {
            return false;
        }

        userRepository.delete(u);
        return true;
    }

    @Override
    public boolean changeAccountStatus(Long userId, AccountStatus newStatus) {
        User u = getUser(userId);
        if (u == null) {
            return false;
        }

        u.setAccountStatus(newStatus);
        userRepository.save(u);
        return true;
    }

    @Override
    public boolean addRole(Long userId, Long roleId) {
        try {
            Role role = roleRepository.findById(roleId).get();
            User user = userRepository.findById(userId).get();
            user.addRole(role);
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean removeRole(Long userId, Long roleId) {
        try {
            Role role = roleRepository.findById(roleId).get();
            User user = userRepository.findById(userId).get();
            user.removeRole(role);
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Set<Role> getUserRoles(Long userId) {
        try {
            User user = userRepository.findById(userId).get();
            return user.getRoles();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public User getUser(String username) {
        return userRepository.findByUsername(username);
    }

}
