package com.ftnxml.vehiclemanagement.service;

import java.util.List;

import com.ftnxml.vehiclemanagement.dto.VehicleDto;
import com.ftnxml.vehiclemanagement.model.Vehicle;

public interface VehicleService {

    List<Vehicle> getAllVehicles();

    List<Vehicle> getVehiclesByLocation(String location);

    Vehicle getVehicle(Long id);

    boolean removeVehicle(Long id);

    boolean addVehicle(VehicleDto newVehicle);

    List<Vehicle> getVehiclesOfModel(Long modelId);
}
