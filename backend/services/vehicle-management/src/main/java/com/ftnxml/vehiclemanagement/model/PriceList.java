package com.ftnxml.vehiclemanagement.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "pricelist")
public class PriceList {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="pricelist_id")
	private Long id;
	@Column(name ="daily_price")
	private float dailyPrice;
	@Column(name= "mileage_penalty")
	private float mileagePenaltyPrice;
	@OneToMany(mappedBy="priceList")
    private Set<Vehicle> vehicles;

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

	public Set<Vehicle> getVehicles() {
		return vehicles;
	}

	public void setVehicles(Set<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}

}
