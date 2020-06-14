package com.ftnxml.orderprocessing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ftnxml.orderprocessing.model.OrderRequest;
public interface OrderRequestRepository extends JpaRepository<OrderRequest, Long> {
	
	List<OrderRequest> findByVehicleId(Long vehicleId);
	
	List<OrderRequest> findByUserId(Long userId);
	
	List<OrderRequest> findByOwnerId(Long ownerId);

}
