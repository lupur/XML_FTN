package com.ftnxml.orderprocessing.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftnxml.orderprocessing.model.OrderRequest;
import com.ftnxml.orderprocessing.model.VehicleOrder;
import com.ftnxml.orderprocessing.repository.OrderRequestRepository;

@Service
public class OrderRequestServiceImpl implements OrderRequestService {
	
	@Autowired
	OrderRequestRepository orderRequestRepository;

	@Override
	public List<OrderRequest> getAllOrderRequests() {
		try {
			return orderRequestRepository.findAll();
		} catch(Exception e) { 
			return null;
		}
	}

	@Override
	public OrderRequest getOrderRequest(Long id) {
		try {
			return orderRequestRepository.findById(id).get();
		} catch(Exception e) { 
			return null;
		}
	}

	@Override
	public boolean removeOrderRequest(Long id) {
		try {
			orderRequestRepository.deleteById(id);
			return true;
		} catch(Exception e) { 
			return false;
		}
	}

	@Override
	public boolean addOrderRequest(OrderRequest newOrderRequest) {
		if(getOrderRequest(newOrderRequest.getId()) != null) {
			return false;
		}
		for(VehicleOrder vo : newOrderRequest.getVehicleOrders()) {
			vo.setOrderRequest(newOrderRequest);
		}
		orderRequestRepository.save(newOrderRequest);
		return true;
	}

	@Override
	public List<OrderRequest> getOrderRequestByVehicleId(Long vehicleId) {
		try {
			return orderRequestRepository.findByVehicleId(vehicleId);
		} catch(Exception e) { 
			return null;
		}
	}

	@Override
	public List<OrderRequest> getOrderRequestByUserId(Long userId) {
		try {
			return orderRequestRepository.findByUserId(userId);
		} catch(Exception e) { 
			return null;
		}
	}

	@Override
	public List<OrderRequest> getOrderRequestByOwnerId(Long ownerId) {
		try {
			return orderRequestRepository.findByOwnerId(ownerId);
		} catch(Exception e) { 
			return null;
		}
	}

}
