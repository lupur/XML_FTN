package com.ftnxml.orderprocessing.model;

import java.util.Date;

public class VehicleOrder {
	private Long id;
	private Long vehicleId;
	private OrderRequest orderRequest;
	private float totalPrice;
	private Date pickupDate;
	private Date returnDate;

	public VehicleOrder() {
		super();
	}

	public VehicleOrder(Long id, Long vehicleId, OrderRequest orderRequest, float totalPrice, Date pickupDate,
	        Date returnDate) {
		super();
		this.id = id;
		this.vehicleId = vehicleId;
		this.orderRequest = orderRequest;
		this.totalPrice = totalPrice;
		this.pickupDate = pickupDate;
		this.returnDate = returnDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(Long vehicleId) {
		this.vehicleId = vehicleId;
	}

	public OrderRequest getOrderRequest() {
		return orderRequest;
	}

	public void setOrderRequest(OrderRequest orderRequest) {
		this.orderRequest = orderRequest;
	}

	public float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Date getPickupDate() {
		return pickupDate;
	}

	public void setPickupDate(Date pickupDate) {
		this.pickupDate = pickupDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

}
