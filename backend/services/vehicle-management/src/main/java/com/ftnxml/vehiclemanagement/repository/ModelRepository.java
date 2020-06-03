package com.ftnxml.vehiclemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ftnxml.vehiclemanagement.model.Model;

public interface ModelRepository extends JpaRepository<Model, Long> {

}
