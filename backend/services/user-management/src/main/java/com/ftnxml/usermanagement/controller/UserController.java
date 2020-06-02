package com.ftnxml.usermanagement.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftnxml.usermanagement.dto.UserDto;
import com.ftnxml.usermanagement.model.User;
import com.ftnxml.usermanagement.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String home() {
        return "Hello from User-Management Service";
    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity registerUser(@RequestBody UserDto newUser) {

        if (!newUser.getPassword().equals(newUser.getConfirmPassword())) {
            return ResponseEntity.badRequest().body("Password confirmation invalid.");
        }
        User user = modelMapper.map(newUser, User.class);
        if (!userService.RegisterNewUser(user)) {
            return ResponseEntity.badRequest().body("Username taken.");
        }
        return ResponseEntity.ok("Registration completed.");
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getUser(@PathVariable Long id) {
        // TODO Implement me
        return null;
    }
}
