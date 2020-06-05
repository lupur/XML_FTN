package com.ftnxml.vehiclemanagement.dto;

public class VehicleDto {
	private Long modelId;
	private Long pricelistId;
	private Long colDamageWaiverId;
	private Long discountId;
	private Long userId;
	private Long fuelTypeId;
	private Long transmissionTypeId;
	private Long classTypeId;
	private int mileage;
	private int mileageConstraint;
	private boolean insurance;
	private int numberOfSeats;
	private float rating;
	private String location;

	public Long getModelId() {
		return modelId;
	}

	public void setModelId(Long modelId) {
		this.modelId = modelId;
	}

	public Long getPricelistId() {
		return pricelistId;
	}

	public void setPricelistId(Long pricelistId) {
		this.pricelistId = pricelistId;
	}

	public Long getColDamageWaiverId() {
		return colDamageWaiverId;
	}

	public void setColDamageWaiverId(Long colDamageWaiverId) {
		this.colDamageWaiverId = colDamageWaiverId;
	}

	public Long getDiscountId() {
		return discountId;
	}

	public void setDiscountId(Long discountId) {
		this.discountId = discountId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getFuelTypeId() {
		return fuelTypeId;
	}

	public void setFuelTypeId(Long fuelTypeId) {
		this.fuelTypeId = fuelTypeId;
	}

	public Long getTransmissionTypeId() {
		return transmissionTypeId;
	}

	public void setTransmissionTypeId(Long transmissionTypeId) {
		this.transmissionTypeId = transmissionTypeId;
	}

	public Long getClassTypeId() {
		return classTypeId;
	}

	public void setClassTypeId(Long classTypeId) {
		this.classTypeId = classTypeId;
	}

	public int getMileage() {
		return mileage;
	}

	public void setMileage(int mileage) {
		this.mileage = mileage;
	}

	public int getMileageConstraint() {
		return mileageConstraint;
	}

	public void setMileageConstraint(int mileageConstraint) {
		this.mileageConstraint = mileageConstraint;
	}

	public boolean isInsurance() {
		return insurance;
	}

	public void setInsurance(boolean insurance) {
		this.insurance = insurance;
	}

	public int getNumberOfSeats() {
		return numberOfSeats;
	}

	public void setNumberOfSeats(int numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
}