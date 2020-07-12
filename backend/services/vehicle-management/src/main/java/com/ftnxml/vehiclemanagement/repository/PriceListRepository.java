package com.ftnxml.vehiclemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ftnxml.vehiclemanagement.model.PriceList;

public interface PriceListRepository extends JpaRepository<PriceList, Long> {
    PriceList findByDailyPrice(Double price);
}
