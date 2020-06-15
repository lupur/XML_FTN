package com.ftnxml.orderprocessing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ftnxml.orderprocessing.model.OrderRequest;
public interface OrderRequestRepository extends JpaRepository<OrderRequest, Long> {
	
	@Query("SELECT vo.orderRequest FROM VehicleOrder vo WHERE vo.vehicleId = :vehicleId")
	List<OrderRequest> findByVehicleId(@Param("vehicleId") Long vehicleId);
	
	List<OrderRequest> findByUserId(Long userId);
	
	List<OrderRequest> findByOwnerId(Long ownerId);

}
