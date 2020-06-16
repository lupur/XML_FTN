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

import com.ftnxml.vehiclemanagement.dto.PriceListDto;
import com.ftnxml.vehiclemanagement.model.PriceList;
import com.ftnxml.vehiclemanagement.service.PriceListService;

@RestController
@RequestMapping("/priceLists")
public class PriceListController {

    @Autowired
    PriceListService priceListService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getPriceLists() {
        List<PriceListDto> prices = priceListService.getAllPriceLists().stream()
                .map(pl -> modelMapper.map(pl, PriceListDto.class)).collect(Collectors.toList());
        return ResponseEntity.ok(prices);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getPriceList(@PathVariable Long id) {
        PriceList pl = priceListService.getPriceList(id);
        if (pl == null)
            return ResponseEntity.notFound().build();

        PriceListDto pld = modelMapper.map(pl, PriceListDto.class);
        return ResponseEntity.ok(pld);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity removePriceList(@PathVariable Long id) {
        if (priceListService.removePriceList(id))
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.notFound().build();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addPriceList(@RequestBody PriceListDto priceList) {
        if (priceList == null)
            return ResponseEntity.badRequest().build();

        PriceList pl = new PriceList(null, priceList.getDailyPrice(), priceList.getMileagePenaltyPrice());
        if (priceListService.addPriceList(pl))
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.badRequest().build();
    }
}
