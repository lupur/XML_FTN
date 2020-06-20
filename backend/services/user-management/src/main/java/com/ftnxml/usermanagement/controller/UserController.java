package com.ftnxml.usermanagement.controller;

import java.util.Date;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftnxml.usermanagement.dto.UserDto;
import com.ftnxml.usermanagement.enums.AccountStatus;
import com.ftnxml.usermanagement.model.User;
import com.ftnxml.usermanagement.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*", allowedHeaders = "*")
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
        User user = new User();
        user.setAccountStatus(AccountStatus.INACTIVE);
        user.setEmail(newUser.getEmail());
        user.setPassword(newUser.getPassword());
        user.setRegisterDate(new Date());
        user.setUsername(newUser.getUsername());

        if (!userService.registerNewUser(user)) {
            return ResponseEntity.badRequest().body("Unable to register user with given data.");
        }
        return ResponseEntity.ok("Registration completed.");
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getUser(@PathVariable Long id) {
        User user = userService.getUser(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        UserDto userDto = new UserDto();
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        return ResponseEntity.ok(userDto);
    }

    @PutMapping(value = "/{id}/inactive", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity inactivateUser(@PathVariable Long id) {
        if (userService.changeAccountStatus(id, AccountStatus.INACTIVE))
            return ResponseEntity.ok("User intactivated.");
        else
            return ResponseEntity.badRequest().body("No user with given id");
    }

    @PutMapping(value = "/{id}/activate", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity activateUser(@PathVariable Long id) {
        if (userService.changeAccountStatus(id, AccountStatus.ACTIVE))
            return ResponseEntity.ok("User activated.");
        else
            return ResponseEntity.badRequest().body("No user with given id");
    }

    @PutMapping(value = "/{id}/block", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity blockUser(@PathVariable Long id) {
        if (userService.changeAccountStatus(id, AccountStatus.BLOCKED))
            return ResponseEntity.ok("User blocked.");
        else
            return ResponseEntity.badRequest().body("No user with given id");
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity removeUser(@PathVariable Long id) {
        if (userService.removeUser(id))
            return ResponseEntity.ok("User removed.");
        else
            return ResponseEntity.badRequest().body("No user with given id");
    }
}
