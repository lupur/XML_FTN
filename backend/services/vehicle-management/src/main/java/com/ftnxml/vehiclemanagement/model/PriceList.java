package com.ftnxml.vehiclemanagement.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pricelists")
public class PriceList {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private float dailyPrice;
	private float mileagePenaltyPrice;

	public PriceList() {
		super();
	}

	public PriceList(Long id, float dailyPrice, float mileagePenaltyPrice) {
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
