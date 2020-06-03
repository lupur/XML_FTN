package com.ftnxml.vehiclemanagement.service;

import java.util.List;

import com.ftnxml.vehiclemanagement.model.FuelType;

public interface FuelTypeService {

    List<FuelType> getAllFuelTypes();

    FuelType getFuelType(Long id);

    boolean removeFuelType(Long id);

    boolean addFuelType(FuelType newFuelType);
}
