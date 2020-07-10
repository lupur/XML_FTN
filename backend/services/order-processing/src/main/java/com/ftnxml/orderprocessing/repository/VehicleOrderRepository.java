package com.ftnxml.orderprocessing.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ftnxml.orderprocessing.model.VehicleOrder;

public interface VehicleOrderRepository extends JpaRepository<VehicleOrder, Long> {
	
	List<VehicleOrder> findByOrderRequest_Id(Long requestId);
	
	List<VehicleOrder> findByVehicleId(Long vehicleId);
	
	@Query(
			value = "SELECT vo.vehicle_id FROM order_service.vehicle_order as vo \r\n" + 
					"INNER JOIN order_service.order_request AS r USING (request_id) \r\n" + 
					"WHERE vo.vehicle_id IN :vehicleIDs \r\n" + 
					"AND r.request_status IN (2,3) \r\n" + 
					"AND vo.pickup_date BETWEEN :startDate AND :endDate \r\n" + 
					"OR vo.return_date BETWEEN :startDate AND :endDate",
			nativeQuery = true)
	List<Long> findVehiclesUnavailableInRange(@Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("vehicleIDs") List<Long> vehicleIDs);

}
