package com.ftnxml.orderprocessing.service;

import java.util.List;

import com.ftnxml.orderprocessing.model.OrderRequest;

public interface OrderRequestService {

	List<OrderRequest>getAllOrderRequests();
	
	OrderRequest getOrderRequest(Long id);
	
	boolean removeOrderRequest(Long id);
	
	boolean addOrderRequest(OrderRequest newOrderRequest);

	List<OrderRequest> getOrderRequestByVehicleId(Long vehicleId);
	
	List<OrderRequest> getOrderRequestByUserId(Long userId);
	
	List<OrderRequest> getOrderRequestByOwnerId(Long ownerId);
	
}