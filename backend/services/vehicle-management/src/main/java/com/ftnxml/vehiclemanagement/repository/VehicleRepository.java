package com.ftnxml.vehiclemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ftnxml.vehiclemanagement.model.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

}
