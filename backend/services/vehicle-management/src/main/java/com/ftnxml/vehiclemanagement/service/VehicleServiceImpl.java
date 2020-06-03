package com.ftnxml.vehiclemanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftnxml.vehiclemanagement.model.Vehicle;
import com.ftnxml.vehiclemanagement.repository.VehicleRepository;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    VehicleRepository vehicleRepository;

    @Override
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    @Override
    public Vehicle getVehicle(Long id) {
        try {
            Vehicle b = vehicleRepository.findById(id).get();
            return b;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean removeVehicle(Long id) {
        try {
            Vehicle b = vehicleRepository.findById(id).get();
            vehicleRepository.delete(b);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean addVehicle(Vehicle newVehicle) {
        if (getVehicle(newVehicle.getId()) != null) {
            return false;
        }

        vehicleRepository.save(newVehicle);
        return true;
    }

    @Override
    public List<Vehicle> getVehiclesOfModel(Long modelId) {
        return vehicleRepository.findByModel_Id(modelId);
    }

}
