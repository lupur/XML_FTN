package com.ftnxml.orderprocessing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ftnxml.orderprocessing.model.VehicleOrder;

public interface VehicleOrderRepository extends JpaRepository<VehicleOrder, Long> {
	
	List<VehicleOrder> findByOrderRequest_Id(Long requestId);

}
