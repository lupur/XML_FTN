package com.ftnxml.vehiclemanagement.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ftnxml.vehiclemanagement.dto.VehicleDto;
import com.ftnxml.vehiclemanagement.model.Vehicle;
import com.ftnxml.vehiclemanagement.service.VehicleService;

@RestController
@RequestMapping("/")
public class VehicleController {

    @Autowired
    VehicleService vehicleService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getVehicles() {
        List<Vehicle> vehicles = vehicleService.getAllVehicles();

        return ResponseEntity.ok(convertToDtoList(vehicles));
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getVehicle(@PathVariable Long id) {
        return ResponseEntity.ok(convertToDto(vehicleService.getVehicle(id)));
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity removeVehicle(@PathVariable Long id) {
        if (vehicleService.removeVehicle(id))
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.notFound().build();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addVehicle(@RequestBody VehicleDto newVehicle) {
        System.out.println("Vehicle: " + newVehicle.toString());

        if (newVehicle == null) {
            System.out.println("Vehicle: is null");
            return ResponseEntity.badRequest().build();
        }

        if (vehicleService.addVehicle(newVehicle))
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.badRequest().build();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/custom")
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

        List<Vehicle> filteredVehicles = vehicleService.getVehiclesByLocation(customQuery.get("location"));

        if (customQuery.containsKey("model")) {
            for (Vehicle v : filteredVehicles) {
                if (v.getModel().getId() != Long.parseLong(customQuery.get("model"))) {
                    filteredVehicles.remove(v);
                }
            }
        }

        if (customQuery.containsKey("pricelist")) {
            for (Vehicle v : filteredVehicles) {
                if (v.getPriceList().getId() != Long.parseLong(customQuery.get("pricelist"))) {
                    filteredVehicles.remove(v);
                }
            }
        }

        if (customQuery.containsKey("collisiondamagewaiver")) {
            for (Vehicle v : filteredVehicles) {
                if (v.getColDamageWaiver().getId() != Long.parseLong(customQuery.get("collisiondamagewaiver"))) {
                    filteredVehicles.remove(v);
                }
            }
        }

        if (customQuery.containsKey("discount")) {
            for (Vehicle v : filteredVehicles) {
                if (v.getDiscount().getId() != Long.parseLong(customQuery.get("discount"))) {
                    filteredVehicles.remove(v);
                }
            }
        }

        if (customQuery.containsKey("fueltype")) {
            for (Vehicle v : filteredVehicles) {
                if (v.getFuelType().getId() != Long.parseLong(customQuery.get("fueltype"))) {
                    filteredVehicles.remove(v);
                }
            }
        }

        if (customQuery.containsKey("transmissiontype")) {
            for (Vehicle v : filteredVehicles) {
                if (v.getTransmissionType().getId() != Long.parseLong(customQuery.get("transmissiontype"))) {
                    filteredVehicles.remove(v);
                }
            }
        }

        if (customQuery.containsKey("classtype")) {
            for (Vehicle v : filteredVehicles) {
                if (v.getClassType().getId() != Long.parseLong(customQuery.get("classtype"))) {
                    filteredVehicles.remove(v);
                }
            }
        }

        if (customQuery.containsKey("maxmileage")) {
            for (Vehicle v : filteredVehicles) {
                if (v.getMileage() > Integer.parseInt(customQuery.get("maxmileage"))) {
                    filteredVehicles.remove(v);
                }
            }
        }

        if (customQuery.containsKey("insurance")) {
            for (Vehicle v : filteredVehicles) {
                if (v.isInsurance() != Boolean.parseBoolean(customQuery.get("insurance"))) {
                    filteredVehicles.remove(v);
                }
            }
        }

        if (customQuery.containsKey("numberofseats")) {
            for (Vehicle v : filteredVehicles) {
                if (v.getNumberOfSeats() != Integer.parseInt(customQuery.get("numberofseats"))) {
                    filteredVehicles.remove(v);
                }
            }
        }

        if (customQuery.containsKey("rating")) {
            for (Vehicle v : filteredVehicles) {
                if (v.getRating() < Float.parseFloat(customQuery.get("rating"))) {
                    filteredVehicles.remove(v);
                }
            }
        }

        return ResponseEntity.ok(convertToDtoList(filteredVehicles));
    }

    public VehicleDto convertToDto(Vehicle vehicle) {
        VehicleDto vDto = new VehicleDto();
        if (vehicle.getClassType() != null)
            vDto.setClassTypeId(vehicle.getClassType().getId());
        if (vehicle.getColDamageWaiver() != null)
            vDto.setColDamageWaiverId(vehicle.getColDamageWaiver().getId());
        if (vehicle.getDiscount() != null)
            vDto.setDiscountId(vehicle.getDiscount().getId());
        if (vehicle.getFuelType() != null)
            vDto.setFuelTypeId(vehicle.getFuelType().getId());
        vDto.setInsurance(vehicle.isInsurance());
        if (vehicle.getLocation() != null)
            vDto.setLocation(vehicle.getLocation());
        vDto.setMileage(vehicle.getMileage());
        vDto.setMileageConstraint(vehicle.getMileageConstraint());
        vDto.setRating(vehicle.getRating());
        if (vehicle.getTransmissionType() != null)
            vDto.setTransmissionTypeId(vehicle.getTransmissionType().getId());
        vDto.setUserId(vehicle.getUserId());
        return vDto;
    }

    public List<VehicleDto> convertToDtoList(List<Vehicle> vehicleList) {
        List<VehicleDto> vehiclesDto = new ArrayList<>();
        for (Vehicle v : vehicleList) {
            vehiclesDto.add(convertToDto(v));
        }
        return vehiclesDto;
    }
    // TODO: Create update endpoint

}
