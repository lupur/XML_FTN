package com.ftnxml.vehiclemanagement.model;

public class CollisionDamageWaiver {
	private Long id;
	private double price;

	public CollisionDamageWaiver() {
		super();
	}

	public CollisionDamageWaiver(Long id, double price) {
		super();
		this.id = id;
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
