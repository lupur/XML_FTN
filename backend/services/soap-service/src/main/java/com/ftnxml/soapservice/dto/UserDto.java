package com.ftnxml.soapservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserDto {

    private String username;
    private String password;
    private String confirmPassword;
    private String email;
    @JsonIgnore
    private Long id;
    @JsonIgnore
    private String role;

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

    @Override
    public String toString() {
        return "UserDto [username=" + username + ", password=" + password + ", confirmPassword=" + confirmPassword
                + ", email=" + email + ", id=" + id + ", role=" + role + "]";
    }

}
