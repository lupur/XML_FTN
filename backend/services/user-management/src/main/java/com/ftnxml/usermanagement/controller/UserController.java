package com.ftnxml.usermanagement.controller;

import java.util.Date;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftnxml.usermanagement.dto.UserDto;
import com.ftnxml.usermanagement.enums.AccountStatus;
import com.ftnxml.usermanagement.model.User;
import com.ftnxml.usermanagement.service.UserService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

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

    private final String secret = "JwtSecretKey";

    @GetMapping("/info")
    public ResponseEntity getUserDetails(@RequestHeader("Authorization") String token) {
        System.out.println("Into User Details Controller");
        token = token.replace("Bearer", "");

        Claims claims = Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();

        String username = claims.getSubject();
        if (username != null) {
            @SuppressWarnings("unchecked")
            List<String> authorities = (List<String>) claims.get("authorities");

            System.out.println("Username: " + username);
            System.out.println("Authorities: " + authorities.get(0).toString());
            System.out.println("----------------------------------------------");

            User user = userService.getUser(username);
            UserDto userDto = new UserDto(user);
            return ResponseEntity.ok().body(userDto);

        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping(value = "/registerAgent", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity registerAgent(@RequestBody UserDto newUser) {

        System.out.println("Into register agent");
        if (newUser == null || newUser.getUsername() == null || newUser.getEmail() == null
                || newUser.getPassword() == null || newUser.getConfirmPassword() == null
                || newUser.getUsername().isEmpty() || newUser.getEmail().isEmpty() || newUser.getPassword().isEmpty()
                || newUser.getConfirmPassword().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        if (!newUser.getPassword().equals(newUser.getConfirmPassword())) {
            return ResponseEntity.badRequest().body("Password confirmation invalid.");
        }
        User user = new User();
        user.setAccountStatus(AccountStatus.ACTIVE);
        user.setEmail(newUser.getEmail());
        user.setPassword(newUser.getPassword());
        user.setRegisterDate(new Date());
        user.setUsername(newUser.getUsername());

        Long id = userService.registerNewAgent(user);
        if (id == -1) {
            return ResponseEntity.badRequest().body(id);
        }

        newUser.setId(id);
        return ResponseEntity.ok().body(newUser);
    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity registerUser(@RequestBody UserDto newUser) {

        if (newUser == null || newUser.getUsername() == null || newUser.getEmail() == null
                || newUser.getPassword() == null || newUser.getConfirmPassword() == null
                || newUser.getUsername().isEmpty() || newUser.getEmail().isEmpty() || newUser.getPassword().isEmpty()
                || newUser.getConfirmPassword().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
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
