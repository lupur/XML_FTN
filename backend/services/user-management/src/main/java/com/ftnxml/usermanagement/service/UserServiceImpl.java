package com.ftnxml.usermanagement.service;

import java.util.List;
import java.util.Optional;

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

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public boolean RegisterNewUser(User newUser) {
        if (userRepository.findByUsername(newUser.getUsername()) != null) {
            return false;
        }
        newUser.setPassword(encoder.encode(newUser.getPassword()));
        // TODO Add role
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
    public boolean RemoveUser(Long userId) {
        User u = getUser(userId);
        if (u == null) {
            return false;
        }

        userRepository.delete(u);
        return true;
    }

    @Override
    public boolean ChangeAccountStatus(Long userId, AccountStatus newStatus) {
        User u = getUser(userId);
        if (u == null) {
            return false;
        }

        u.setAccountStatus(newStatus);
        userRepository.save(u);
        return true;
    }

    // FIXME: User can now have multiple roles
//    @Override
//    public boolean ChangeRole(Long userId, String roleName) {
//        Role newRole = roleRepository.findByName(roleName);
//        if (newRole == null) {
//            return false;
//        }
//
//        User u = getUser(userId);
//        if (u == null) {
//            return false;
//        }
//
//        u.setRole(newRole);
//        userRepository.save(u);
//        return true;
//    }
    
    // TODO: REMOVE
    public boolean ChangeRole(Long userId, String roleName) {
    	return true;
    }
    


}
