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
    private String brand;
    private String model;
    private Long userId;
    private Float rating;
    private String location;

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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
