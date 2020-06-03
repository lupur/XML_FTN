package com.ftnxml.vehiclemanagement.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class VehicleController {

    @GetMapping("/")
    public String home() {
        return "Hello from Vehicle-Management Service";
    }
}
