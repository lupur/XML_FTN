package com.ftnxml.orderprocessing.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftnxml.orderprocessing.model.OrderRequest;
import com.ftnxml.orderprocessing.model.VehicleOrder;
import com.ftnxml.orderprocessing.repository.OrderRequestRepository;
import com.ftnxml.orderprocessing.repository.VehicleOrderRepository;

@Service
public class VehicleOrderServiceImpl implements VehicleOrderService {

	@Autowired
	VehicleOrderRepository vehicleOrderRepository;
	
	@Autowired
	OrderRequestRepository orderRequestRepository;
	
	@Override
	public List<VehicleOrder> getAllVehicleOrders() {
		try {
			return vehicleOrderRepository.findAll();
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<VehicleOrder> getVehicleOrdersByRequest(Long requestId) {
		try {
			return vehicleOrderRepository.findByOrderRequest_Id(requestId);
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public VehicleOrder getVehicleOrder(Long id) {
		try {
			return vehicleOrderRepository.findById(id).get();
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean addVehicleOrder(VehicleOrder newVehicleOrder) {
		try {
			vehicleOrderRepository.save(newVehicleOrder);
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public List<Long> findVehiclesAvailableInRange(Date startDate, Date endDate, List<Long> vehicleIDs) {
		try {
			List<Long> vehicleIDsResponse =  vehicleOrderRepository.findVehiclesAvailableInRange(startDate, endDate, vehicleIDs);
			for(Long vehicleID : vehicleIDs) {
				List<OrderRequest> oReqs = orderRequestRepository.findByVehicleId(vehicleID);
				if(oReqs == null || oReqs.isEmpty()) vehicleIDsResponse.add(vehicleID);
			}
			return vehicleIDsResponse;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
