package com.ftnxml.orderprocessing.dto;

import java.util.Date;
import java.util.List;

import com.ftnxml.orderprocessing.enums.OrderRequestStatus;

public class OrderRequestDto {

	private Long id;
	private OrderRequestStatus status;
	private Long userId;
	private Long ownerId;
	private Date createdOn;
	private List<VehicleOrderDto> vehicleOrders;
	
	public OrderRequestDto() {
		
	}

	public OrderRequestDto(Long id, OrderRequestStatus status, Long userId, Long ownerId, Date createdOn,
			List<VehicleOrderDto> vehicleOrders) {
		super();
		this.id = id;
		this.status = status;
		this.userId = userId;
		this.ownerId = ownerId;
		this.createdOn = createdOn;
		this.vehicleOrders = vehicleOrders;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public OrderRequestStatus getStatus() {
		return status;
	}

	public void setStatus(OrderRequestStatus status) {
		this.status = status;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public List<VehicleOrderDto> getVehicleOrders() {
		return vehicleOrders;
	}

	public void setVehicleOrders(List<VehicleOrderDto> vehicleOrders) {
		this.vehicleOrders = vehicleOrders;
	}
	
}
