package com.ftnxml.vehiclemanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftnxml.vehiclemanagement.dto.FuelTypeDto;
import com.ftnxml.vehiclemanagement.model.FuelType;
import com.ftnxml.vehiclemanagement.service.FuelTypeService;

@RestController
@RequestMapping("/fuelTypes")
public class FuelTypeController {

    @Autowired
    FuelTypeService fuelTypeService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getFuelTypes() {
        return ResponseEntity.ok(fuelTypeService.getAllFuelTypes());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getFuelType(@PathVariable Long id) {
        return ResponseEntity.ok(fuelTypeService.getFuelType(id));
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity removeFuelType(@PathVariable Long id) {
        if (fuelTypeService.removeFuelType(id))
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.notFound().build();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addFuelType(@RequestBody FuelTypeDto newFuelType) {
        if (newFuelType == null || newFuelType.getName().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        FuelType fuelType = new FuelType();
        fuelType.setName(newFuelType.getName());
        if (fuelTypeService.addFuelType(fuelType))
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.badRequest().build();
    }

    // TODO: Create update endpoint
}
