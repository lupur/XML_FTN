package com.ftnxml.vehiclemanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftnxml.vehiclemanagement.service.DiscountService;

@RestController
@RequestMapping("/discounts/")
public class DiscountController {

    @Autowired
    DiscountService discountService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getDiscounts() {
        return ResponseEntity.ok(discountService.getAllDiscounts());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getDiscount(@PathVariable Long id) {
        return ResponseEntity.ok(discountService.getDiscount(id));
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity removeDiscount(@PathVariable Long id) {
        if (discountService.removeDiscount(id))
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.notFound().build();
    }

    // TODO: Create update endpoint
}
