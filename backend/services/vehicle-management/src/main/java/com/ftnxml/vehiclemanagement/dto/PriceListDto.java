package com.ftnxml.vehiclemanagement.dto;

public class PriceListDto {
	private float dailyPrice;
	private float mileagePenaltyPrice;

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
