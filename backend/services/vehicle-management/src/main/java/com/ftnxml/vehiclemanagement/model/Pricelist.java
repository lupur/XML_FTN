package com.ftnxml.vehiclemanagement.model;

public class Pricelist {
	private Long id;
	private float dailyPrice;
	private float mileagePenaltyPrice;

	public Pricelist() {
		super();
	}

	public Pricelist(Long id, float dailyPrice, float mileagePenaltyPrice) {
		super();
		this.id = id;
		this.dailyPrice = dailyPrice;
		this.mileagePenaltyPrice = mileagePenaltyPrice;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public float getDailyPrice() {
		return dailyPrice;
	}

	public void setDailyPrice(float dailyPrice) {
		this.dailyPrice = dailyPrice;
	}

	public float getMileagePenaltyPrice() {
		return mileagePenaltyPrice;
	}

	public void setMileagePenaltyPrice(float mileagePenaltyPrice) {
		this.mileagePenaltyPrice = mileagePenaltyPrice;
	}

}
