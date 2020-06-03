package com.ftnxml.vehiclemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ftnxml.vehiclemanagement.model.FuelType;

public interface FuelTypeRepository extends JpaRepository<FuelType, Long> {

}
