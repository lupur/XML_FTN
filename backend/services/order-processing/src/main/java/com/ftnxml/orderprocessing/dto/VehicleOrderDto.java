package com.ftnxml.orderprocessing.dto;

import java.util.Date;

import com.ftnxml.orderprocessing.model.OrderRequest;

public class VehicleOrderDto {
	
	private Long id;
	private Long vehicleId;
//	private OrderRequest orderRequest;
	private Double totalPrice;
	private Date pickupDate;
	private Date returnDate;
	private ReportDto report;
	
	public VehicleOrderDto() {
		
	}

	public VehicleOrderDto(Long id, Long vehicleId, OrderRequest orderRequest, Double totalPrice, Date pickupDate,
			Date returnDate) {
		this.id = id;
		this.vehicleId = vehicleId;
//		this.orderRequest = orderRequest;
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

//	public OrderRequest getOrderRequest() {
//		return orderRequest;
//	}
//
//	public void setOrderRequest(OrderRequest orderRequest) {
//		this.orderRequest = orderRequest;
//	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
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

	public ReportDto getReport() {
		return report;
	}

	public void setReport(ReportDto report) {
		this.report = report;
	}

}
