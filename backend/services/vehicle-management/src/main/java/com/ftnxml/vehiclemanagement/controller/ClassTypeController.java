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

import com.ftnxml.vehiclemanagement.dto.ClassTypeDto;
import com.ftnxml.vehiclemanagement.model.ClassType;
import com.ftnxml.vehiclemanagement.service.ClassTypeService;

@RestController
@RequestMapping("/classes")
public class ClassTypeController {

    @Autowired
    ClassTypeService classTypeService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getClassTypes() {
        return ResponseEntity.ok(classTypeService.getAllClassTypes());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getClassType(@PathVariable Long id) {
        return ResponseEntity.ok(classTypeService.getClassType(id));
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity removeClassType(@PathVariable Long id) {
        if (classTypeService.removeClassType(id))
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.notFound().build();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addClassType(@RequestBody ClassTypeDto newClassType) {
        if (newClassType == null || newClassType.getName().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        ClassType classType = new ClassType();
        classType.setName(newClassType.getName());
        if (classTypeService.addClassType(classType))
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.badRequest().build();
    }

    // TODO: Create update endpoint
}
