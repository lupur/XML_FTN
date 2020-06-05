package com.ftnxml.vehiclemanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftnxml.vehiclemanagement.service.TransmissionTypeService;

@RestController
@RequestMapping("/transmissionTypes")
public class TransmissionTypeController {

    @Autowired
    TransmissionTypeService transmissionTypeService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getTransmissionTypes() {
        return ResponseEntity.ok(transmissionTypeService.getAllTransmissionTypes());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getTransmissionType(@PathVariable Long id) {
        return ResponseEntity.ok(transmissionTypeService.getTransmissionType(id));
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity removeTransmissionType(@PathVariable Long id) {
        if (transmissionTypeService.removeTransmissionType(id))
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.notFound().build();
    }

    // TODO: Create update endpoint
}
