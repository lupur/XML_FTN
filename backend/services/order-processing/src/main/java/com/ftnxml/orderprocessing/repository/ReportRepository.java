package com.ftnxml.orderprocessing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ftnxml.orderprocessing.model.Report;

public interface ReportRepository extends JpaRepository<Report, Long> {
	
	Report findByVehicleOrder_id(Long vehicleOrderId);

}
