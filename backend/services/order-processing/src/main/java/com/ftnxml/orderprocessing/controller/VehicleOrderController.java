package com.ftnxml.orderprocessing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftnxml.orderprocessing.repository.VehicleOrderRepository;
import com.ftnxml.orderprocessing.service.VehicleOrderService;

@RestController
public class VehicleOrderController {
	
	@Autowired
	VehicleOrderService vehicleOrderService;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity getVehicleOrders() {
		return ResponseEntity.ok(vehicleOrderService.getAllVehicleOrders());
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity getVehicleOrderById(Long vehicleOrderId) {
		return ResponseEntity.ok(vehicleOrderService.getVehicleOrder(vehicleOrderId));
	}

}
