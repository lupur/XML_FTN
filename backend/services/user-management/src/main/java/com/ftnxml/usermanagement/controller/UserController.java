package com.ftnxml.usermanagement.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftnxml.usermanagement.dto.UserDto;

@RestController
@RequestMapping("/user/")
public class UserController {

    @GetMapping("/")
    public String home() {
        return "Hello from User-Management Service";
    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity registerUser(@RequestBody UserDto newUser) {
        return ResponseEntity.ok("Into register controller");
    }
}
