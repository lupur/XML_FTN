package com.ftnxml.usermanagement.dto;

import com.ftnxml.usermanagement.model.Role;
import com.ftnxml.usermanagement.model.User;

public class UserDto {

    private String username;
    private String password;
    private String confirmPassword;
    private String email;
    private Long id;
    private String role;

    public UserDto(User user) {
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.id = user.getId();
        for (Role r : user.getRoles()) {
            this.role = r.getName();
            break;
        }
    }

    public UserDto() {
        super();
        // TODO Auto-generated constructor stub
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
