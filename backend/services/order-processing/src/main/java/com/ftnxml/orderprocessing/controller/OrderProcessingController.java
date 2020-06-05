package com.ftnxml.orderprocessing.controller;

import java.util.ArrayList;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderProcessingController {
	
	public class VehicleController {

	    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity getBrands() {
	        return ResponseEntity.ok(new ArrayList<>());
	    }
	}

}
