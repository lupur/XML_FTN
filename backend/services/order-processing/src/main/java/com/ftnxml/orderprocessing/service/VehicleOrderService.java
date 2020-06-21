package com.ftnxml.orderprocessing.service;

import java.util.Date;
import java.util.List;

import com.ftnxml.orderprocessing.model.VehicleOrder;

public interface VehicleOrderService {
	
	List<VehicleOrder> getAllVehicleOrders();
	
	List<VehicleOrder> getVehicleOrdersByRequest(Long requestId);
	
	VehicleOrder getVehicleOrder(Long id);
	
	boolean addVehicleOrder(VehicleOrder newVehicleOrder);
	
	List<Long> findVehiclesAvailableInRange(Date startDate, Date endDate, List<Long> vehicleIDs);
}
