package com.ftnxml.vehiclemanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftnxml.vehiclemanagement.service.PriceListService;

@RestController
@RequestMapping("/priceLists")
public class PriceListController {

    @Autowired
    PriceListService priceListService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getPriceLists() {
        return ResponseEntity.ok(priceListService.getAllPriceLists());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getPriceList(@PathVariable Long id) {
        return ResponseEntity.ok(priceListService.getPriceList(id));
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity removePriceList(@PathVariable Long id) {
        if (priceListService.removePriceList(id))
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.notFound().build();
    }

    // TODO: Create update endpoint
}
