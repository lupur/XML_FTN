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

import com.ftnxml.vehiclemanagement.dto.TransmissionTypeDto;
import com.ftnxml.vehiclemanagement.model.TransmissionType;
import com.ftnxml.vehiclemanagement.service.TransmissionTypeService;

@RestController
@RequestMapping("/transmissionTypes")
public class TransmissionTypeController {

    @Autowired
    TransmissionTypeService transmissionTypeService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getTransmissionTypes() {
        List<TransmissionTypeDto> transmissions = transmissionTypeService.getAllTransmissionTypes().stream()
                .map(transmission -> modelMapper.map(transmission, TransmissionTypeDto.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(transmissions);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getTransmissionType(@PathVariable Long id) {
        TransmissionType t = transmissionTypeService.getTransmissionType(id);
        if (t == null)
            ResponseEntity.notFound().build();
        TransmissionTypeDto transmissionDto = modelMapper.map(t, TransmissionTypeDto.class);
        return ResponseEntity.ok(transmissionDto);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity removeTransmissionType(@PathVariable Long id) {
        if (transmissionTypeService.removeTransmissionType(id))
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.notFound().build();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addDiscount(@RequestBody TransmissionTypeDto newTransmission) {
        if (newTransmission == null) {
            return ResponseEntity.badRequest().build();
        }

        TransmissionType transmission = new TransmissionType(null, newTransmission.getName());

        if (transmissionTypeService.addTransmissionType(transmission))
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.badRequest().build();
    }
}
