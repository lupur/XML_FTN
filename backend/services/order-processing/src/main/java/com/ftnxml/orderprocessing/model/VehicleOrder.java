package com.ftnxml.orderprocessing.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "vehicle_order")
public class VehicleOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name= "order_id")
	private Long id;
	@Column(name ="vehicle_id")
	private Long vehicleId;
	@ManyToOne
	@JoinColumn(name= "request_id")
	private OrderRequest orderRequest;
	@Column(name ="total_price")
	private float totalPrice;
	@Column(name ="pickup_date")
	private Date pickupDate;
	@Column(name ="return_date")
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
