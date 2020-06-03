package com.ftnxml.vehiclemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ftnxml.vehiclemanagement.model.Discount;

public interface DiscountRepository extends JpaRepository<Discount, Long> {

}
