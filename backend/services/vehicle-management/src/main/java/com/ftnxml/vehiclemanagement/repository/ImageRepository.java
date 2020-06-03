package com.ftnxml.vehiclemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ftnxml.vehiclemanagement.model.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {

}
