package com.ftnxml.vehiclemanagement.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
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

import com.ftnxml.vehiclemanagement.dto.CollisionDamageWaiverDto;
import com.ftnxml.vehiclemanagement.model.CollisionDamageWaiver;
import com.ftnxml.vehiclemanagement.service.CollisionDamageService;

@RestController
@RequestMapping("/collisionDW")
public class CollisionDamageWaiverController {

    @Autowired
    CollisionDamageService collisionDamageService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getCollisionDamageWaivers() {
        List<CollisionDamageWaiverDto> collisions = collisionDamageService.getAllCollisionDW().stream()
                .map(coll -> modelMapper.map(coll, CollisionDamageWaiverDto.class)).collect(Collectors.toList());
        return ResponseEntity.ok(collisions);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getCollisionDamageWaiver(@PathVariable Long id) {
        CollisionDamageWaiver c = collisionDamageService.getCollisionDW(id);
        if (c == null)
            ResponseEntity.notFound().build();
        CollisionDamageWaiverDto cd = modelMapper.map(c, CollisionDamageWaiverDto.class);
        return ResponseEntity.ok(cd);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity removeCollisionDamageWaiver(@PathVariable Long id) {
        if (collisionDamageService.removeCollisionDW(id))
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.notFound().build();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addCollision(@RequestBody CollisionDamageWaiverDto newCDW) {
        if (newCDW == null) {
            return ResponseEntity.badRequest().build();
        }

        CollisionDamageWaiver cdw = new CollisionDamageWaiver(null, newCDW.getPrice());
        if (collisionDamageService.addCollisionDW(cdw))
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.badRequest().build();
    }
}
