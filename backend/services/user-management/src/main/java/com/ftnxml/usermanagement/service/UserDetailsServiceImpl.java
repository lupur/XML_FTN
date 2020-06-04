package com.ftnxml.usermanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ftnxml.usermanagement.model.Role;
import com.ftnxml.usermanagement.model.User;
import com.ftnxml.usermanagement.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println("loadUserByUsername method");
        final User user = userRepository.findByUsername(username);
        if (user == null) {
            // If user not found. Throw this exception.
            throw new UsernameNotFoundException("Username: " + username + " not found");
        }

        String authorityParam = "";

        for (Role role : user.getRoles()) {
            authorityParam += "ROLE_" + role.getName() + ",";
        }
        authorityParam = authorityParam.substring(0, authorityParam.length() - 1);
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(authorityParam);

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                grantedAuthorities);
    }
}