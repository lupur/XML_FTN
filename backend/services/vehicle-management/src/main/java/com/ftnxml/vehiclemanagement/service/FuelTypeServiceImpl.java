package com.ftnxml.vehiclemanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftnxml.vehiclemanagement.model.FuelType;
import com.ftnxml.vehiclemanagement.repository.FuelTypeRepository;

@Service
public class FuelTypeServiceImpl implements FuelTypeService {

    @Autowired
    FuelTypeRepository fuelTypeRepository;

    @Override
    public List<FuelType> getAllFuelTypes() {
        return fuelTypeRepository.findAll();
    }

    @Override
    public FuelType getFuelType(Long id) {
        try {
            FuelType b = fuelTypeRepository.findById(id).get();
            return b;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean removeFuelType(Long id) {
        try {
            FuelType b = fuelTypeRepository.findById(id).get();
            fuelTypeRepository.delete(b);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean addFuelType(FuelType newFuelType) {
        if (getFuelType(newFuelType.getId()) != null) {
            return false;
        }

        fuelTypeRepository.save(newFuelType);
        return true;
    }

}
