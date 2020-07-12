package com.ftnxml.vehiclemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ftnxml.vehiclemanagement.model.Brand;

public interface BrandRepository extends JpaRepository<Brand, Long> {

    Brand findByName(String name);
}
