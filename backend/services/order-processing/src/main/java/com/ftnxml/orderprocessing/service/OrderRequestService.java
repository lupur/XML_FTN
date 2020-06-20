package com.ftnxml.orderprocessing.service;

import java.util.List;

import com.ftnxml.orderprocessing.enums.OrderRequestStatus;
import com.ftnxml.orderprocessing.model.OrderRequest;

public interface OrderRequestService {

	List<OrderRequest> getAllOrderRequests();

	OrderRequest getOrderRequest(Long id);

	boolean removeOrderRequest(Long id);

	boolean addOrderRequest(OrderRequest newOrderRequest);
	
	boolean updateOrderRequest(OrderRequest orderRequest);

	List<OrderRequest> getOrderRequestByVehicleId(Long vehicleId);

	List<OrderRequest> getOrderRequestByUserId(Long userId);

	List<OrderRequest> getOrderRequestByOwnerId(Long ownerId);

	boolean changeOrderRequestStatus(Long orderId, OrderRequestStatus newStatus);
}