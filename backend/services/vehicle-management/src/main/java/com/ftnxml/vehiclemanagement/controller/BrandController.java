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

import com.ftnxml.vehiclemanagement.dto.BrandDto;
import com.ftnxml.vehiclemanagement.model.Brand;
import com.ftnxml.vehiclemanagement.service.BrandService;

@RestController
@RequestMapping("/brands")
public class BrandController {

    @Autowired
    BrandService brandService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getBrands() {
        List<BrandDto> brands = brandService.getAllBrands().stream()
                .map(brand -> modelMapper.map(brand, BrandDto.class)).collect(Collectors.toList());
        return ResponseEntity.ok(brands);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getBrand(@PathVariable Long id) {
        Brand b = brandService.getBrand(id);
        if (b == null)
            return ResponseEntity.notFound().build();
        BrandDto brand = modelMapper.map(b, BrandDto.class);
        return ResponseEntity.ok(brand);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity removeBrand(@PathVariable Long id) {
        if (brandService.removeBrand(id))
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.notFound().build();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addBrand(@RequestBody BrandDto newBrand) {
        if (newBrand == null || newBrand.getName().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        Brand brand = new Brand();
        brand.setName(newBrand.getName());
        if (brandService.addBrand(brand))
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.badRequest().build();
    }
}