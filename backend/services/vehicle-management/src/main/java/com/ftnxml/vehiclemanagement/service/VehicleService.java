package com.ftnxml.vehiclemanagement.service;

import java.util.List;

import com.ftnxml.vehiclemanagement.model.Vehicle;

public interface VehicleService {

    List<Vehicle> getAllVehicles();

    Vehicle getVehicle(Long id);

    boolean removeVehicle(Long id);

    boolean addVehicle(Vehicle newVehicle);
}
