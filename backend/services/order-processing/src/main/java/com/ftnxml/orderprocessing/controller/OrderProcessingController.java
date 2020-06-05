package com.ftnxml.orderprocessing.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class OrderProcessingController {

    public class VehicleController {

        @GetMapping("/")
        public String home() {
            return "Hello from Order-Processing Service";
        }

        @GetMapping(value = "/test")
        public String test() {
            return "Hello from Order-Processing Service";
        }
    }

}
