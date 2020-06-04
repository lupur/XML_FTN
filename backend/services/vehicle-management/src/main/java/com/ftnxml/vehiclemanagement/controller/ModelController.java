package com.ftnxml.vehiclemanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftnxml.vehiclemanagement.service.ModelService;

@RestController
@RequestMapping("/models")
public class ModelController {

    @Autowired
    ModelService modelService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getModels() {
        return ResponseEntity.ok(modelService.getAllModels());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getModel(@PathVariable Long id) {
        return ResponseEntity.ok(modelService.getModel(id));
    }

    // TODO: Create update endpoint
}
