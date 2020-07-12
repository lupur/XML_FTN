package com.ftnxml.vehiclemanagement.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ftnxml.vehiclemanagement.client.UserDetailsClient;
import com.ftnxml.vehiclemanagement.dto.BrandDto;
import com.ftnxml.vehiclemanagement.dto.NewVehicleDto;
import com.ftnxml.vehiclemanagement.dto.NewVehicleSoapDto;
import com.ftnxml.vehiclemanagement.dto.SimpleVehicleDto;
import com.ftnxml.vehiclemanagement.dto.UserDto;
import com.ftnxml.vehiclemanagement.dto.VehicleDto;
import com.ftnxml.vehiclemanagement.model.Model;
import com.ftnxml.vehiclemanagement.model.Vehicle;
import com.ftnxml.vehiclemanagement.service.BrandService;
import com.ftnxml.vehiclemanagement.service.ModelService;
import com.ftnxml.vehiclemanagement.service.VehicleService;

@RestController
@RequestMapping("/")
public class VehicleController {

    @Autowired
    VehicleService vehicleService;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    BrandService brandService;

    @Autowired
    ModelService modelService;

    @Autowired
    UserDetailsClient userDetailsClient;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getVehicles() {
        List<VehicleDto> vehicles = vehicleService.getAllVehicles().stream()
                .map(vehicle -> modelMapper.map(vehicle, VehicleDto.class)).collect(Collectors.toList());

        for (VehicleDto v : vehicles) {
            Model model = modelService.getModel(v.getModel().getId());
            BrandDto bDto = modelMapper.map(model.getBrand(), BrandDto.class);
            v.setBrand(bDto);
        }
        return ResponseEntity.ok(vehicles);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getVehicle(@PathVariable Long id) {
        Vehicle v = vehicleService.getVehicle(id);
        if (v == null)
            return ResponseEntity.notFound().build();
        VehicleDto vdto = modelMapper.map(v, VehicleDto.class);
        return ResponseEntity.ok(vdto);
    }

    @GetMapping(value = "/simple/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getSimpleVehicle(@PathVariable Long id) {
        Vehicle v = vehicleService.getVehicle(id);
        if (v == null)
            return ResponseEntity.notFound().build();
        SimpleVehicleDto vdto = new SimpleVehicleDto(v);
        return ResponseEntity.ok(vdto);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity removeVehicle(@PathVariable Long id) {
        if (vehicleService.removeVehicle(id))
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.notFound().build();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addVehicle(@RequestHeader("Authorization") String token,
            @RequestBody NewVehicleDto newVehicle) {

        UserDto user = userDetailsClient.getUserInfo(token);
        System.out.println("This is user : " + user.getRole().toString());

        if (user.getRole().equals("USER")) {
            System.out.println("User role is USER");
            List<Vehicle> vehicles = vehicleService.getUsersVehicles(user.getId());
            System.out.println("No of vehicles: " + vehicles.size());
            if (vehicles.size() >= 3) {
                System.out.println("Cannot have more than 3 vehicles");
                return ResponseEntity.badRequest().build();
            }
        }

        if (newVehicle == null) {
            System.out.println("Vehicle: is null");
            return ResponseEntity.badRequest().build();
        }
        newVehicle.setUserId(user.getId());

        if (vehicleService.addVehicle(newVehicle))
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.badRequest().build();
    }

    @PostMapping(value = "/soap", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addVehicleSOAP(@RequestBody NewVehicleSoapDto newVehicle) {

        if (newVehicle == null) {
            System.out.println("Vehicle: is null");
            return ResponseEntity.badRequest().build();
        }

        Vehicle v = vehicleService.addVehicle(newVehicle);
        if (v != null) {
            System.out.println("Added vechicle from soap");
            newVehicle.setId(v.getId());
            return ResponseEntity.ok().body(newVehicle);
        } else
            return ResponseEntity.badRequest().body(new NewVehicleSoapDto());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/filter")
    public ResponseEntity getFilteredVehicles(@RequestParam Map<String, String> customQuery) {

        if (!customQuery.containsKey("location")) {
            return ResponseEntity.badRequest().build();
        }

        /*
         * How to get this from the order processing service? if
         * (!customQuery.containsKey("pickupDate")){ return
         * ResponseEntity.badRequest().build(); }
         * 
         * if (!customQuery.containsKey("returnDate")){ return
         * ResponseEntity.badRequest().build(); }
         */

        List<Vehicle> vehicles = vehicleService.getVehiclesByLocation(customQuery.get("location"));
        List<Vehicle> filteredVehicles = new ArrayList<Vehicle>();

        for (Vehicle v : vehicles) {

            if (customQuery.containsKey("model")) {
                if (v.getModel().getId() != Long.parseLong(customQuery.get("model"))) {
                    continue;
                }
            }

            if (customQuery.containsKey("pricelist")) {
                if (v.getPriceList().getId() != Long.parseLong(customQuery.get("pricelist"))) {
                    continue;
                }
            }

            if (customQuery.containsKey("collisiondamagewaiver")) {
                if (v.getColDamageWaiver().getId() != Long.parseLong(customQuery.get("collisiondamagewaiver"))) {
                    continue;
                }
            }

            if (customQuery.containsKey("discount")) {
                if (v.getDiscount().getId() != Long.parseLong(customQuery.get("discount"))) {
                    continue;
                }
            }

            if (customQuery.containsKey("fueltype")) {
                if (v.getFuelType().getId() != Long.parseLong(customQuery.get("fueltype"))) {
                    continue;
                }
            }

            if (customQuery.containsKey("transmissiontype")) {
                if (v.getTransmissionType().getId() != Long.parseLong(customQuery.get("transmissiontype"))) {
                    continue;
                }
            }

            if (customQuery.containsKey("classtype")) {
                if (v.getClassType().getId() != Long.parseLong(customQuery.get("classtype"))) {
                    continue;
                }
            }

            if (customQuery.containsKey("maxmileage")) {
                if (v.getMileage() > Integer.parseInt(customQuery.get("maxmileage"))) {
                    continue;
                }
            }

            if (customQuery.containsKey("insurance")) {
                if (v.isInsurance() != Boolean.parseBoolean(customQuery.get("insurance"))) {
                    continue;
                }
            }

            if (customQuery.containsKey("numberofseats")) {
                if (v.getNumberOfSeats() != Integer.parseInt(customQuery.get("numberofseats"))) {
                    continue;
                }
            }

            if (customQuery.containsKey("rating")) {
                if (v.getRating() < Float.parseFloat(customQuery.get("rating"))) {
                    continue;
                }
            }

            filteredVehicles.add(v);
        }

        List<VehicleDto> vdto = filteredVehicles.stream().map(vehicle -> modelMapper.map(vehicle, VehicleDto.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(vdto);
    }
}
