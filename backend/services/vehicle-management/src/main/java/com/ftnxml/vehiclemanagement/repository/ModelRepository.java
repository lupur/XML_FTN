package com.ftnxml.vehiclemanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ftnxml.vehiclemanagement.model.Model;

public interface ModelRepository extends JpaRepository<Model, Long> {
    List<Model> findByBrand_Id(Long brandId);
}
