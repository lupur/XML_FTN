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

import com.ftnxml.vehiclemanagement.dto.DiscountDto;
import com.ftnxml.vehiclemanagement.model.Discount;
import com.ftnxml.vehiclemanagement.service.DiscountService;

@RestController
@RequestMapping("/discounts/")
public class DiscountController {

    @Autowired
    DiscountService discountService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getDiscounts() {
        List<DiscountDto> discounts = discountService.getAllDiscounts().stream()
                .map(discount -> modelMapper.map(discount, DiscountDto.class)).collect(Collectors.toList());
        return ResponseEntity.ok(discounts);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getDiscount(@PathVariable Long id) {
        Discount d = discountService.getDiscount(id);
        if (d == null)
            ResponseEntity.notFound().build();
        DiscountDto discountDto = modelMapper.map(d, DiscountDto.class);
        return ResponseEntity.ok(discountDto);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity removeDiscount(@PathVariable Long id) {
        if (discountService.removeDiscount(id))
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.notFound().build();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addDiscount(@RequestBody DiscountDto newDiscount) {
        if (newDiscount == null) {
            return ResponseEntity.badRequest().build();
        }

        Discount discount = new Discount(null, newDiscount.getNumberOfDays(), newDiscount.getPercentage(),
                newDiscount.getStartDate(), newDiscount.getEndDate());

        if (discountService.addDiscount(discount))
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.badRequest().build();
    }
}
