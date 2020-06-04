package com.ftnxml.vehiclemanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftnxml.vehiclemanagement.service.CollisionDamageService;

@RestController
@RequestMapping("/collisionDW")
public class CollisionDamageWaiverController {

    @Autowired
    CollisionDamageService collisionDamageService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getCollisionDamageWaivers() {
        return ResponseEntity.ok(collisionDamageService.getAllCollisionDW());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getCollisionDamageWaiver(@PathVariable Long id) {
        return ResponseEntity.ok(collisionDamageService.getCollisionDW(id));
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity removeCollisionDamageWaiver(@PathVariable Long id) {
        if (collisionDamageService.removeCollisionDW(id))
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.notFound().build();
    }

    // TODO: Create update endpoint
}
