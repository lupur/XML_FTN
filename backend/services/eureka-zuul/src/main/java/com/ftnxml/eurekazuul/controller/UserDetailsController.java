package com.ftnxml.eurekazuul.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserDetailsController {

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
        }

        return ResponseEntity.ok().body(claims);

    }
}
