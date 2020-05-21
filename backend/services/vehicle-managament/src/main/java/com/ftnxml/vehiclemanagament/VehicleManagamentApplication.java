package com.ftnxml.vehiclemanagament;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class VehicleManagamentApplication {

    @RequestMapping("/health")
    public String home() {
        return "Hello world";
    }

	public static void main(String[] args) {
		SpringApplication.run(VehicleManagamentApplication.class, args);
	}

}
