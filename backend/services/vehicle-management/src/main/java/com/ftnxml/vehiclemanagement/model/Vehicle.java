package com.ftnxml.vehiclemanagement.model;

import java.util.List;

public class Vehicle {
	private Long id;
	private Model model;
	private Pricelist priceList;
	private CollisionDamageWaiver colDamageWaiver;
	private List<Image> images;
	private Long userId;
	private FuelType fuelType;
	private TransmissionType transmissionType;
	private ClassType classType;
	private int mileage;
	private int mileageConstraint;
	private boolean insurance;
	private int numberOfSeats;
	private float rating;
	private Discount discount;
	private String location;

	public Vehicle() {
		super();
	}

	public Vehicle(Long id, Model model, Pricelist priceList, CollisionDamageWaiver colDamageWaiver, List<Image> images,
	        Long userId, FuelType fuelType, TransmissionType transmissionType, ClassType classType, int mileage,
	        int mileageConstraint, boolean insurance, int numberOfSeats, float rating, Discount discount,
	        String location) {
		super();
		this.id = id;
		this.model = model;
		this.priceList = priceList;
		this.colDamageWaiver = colDamageWaiver;
		this.images = images;
		this.userId = userId;
		this.fuelType = fuelType;
		this.transmissionType = transmissionType;
		this.classType = classType;
		this.mileage = mileage;
		this.mileageConstraint = mileageConstraint;
		this.insurance = insurance;
		this.numberOfSeats = numberOfSeats;
		this.rating = rating;
		this.discount = discount;
		this.location = location;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public Pricelist getPriceList() {
		return priceList;
	}

	public void setPriceList(Pricelist priceList) {
		this.priceList = priceList;
	}

	public CollisionDamageWaiver getColDamageWaiver() {
		return colDamageWaiver;
	}

	public void setColDamageWaiver(CollisionDamageWaiver colDamageWaiver) {
		this.colDamageWaiver = colDamageWaiver;
	}

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public FuelType getFuelType() {
		return fuelType;
	}

	public void setFuelType(FuelType fuelType) {
		this.fuelType = fuelType;
	}

	public TransmissionType getTransmissionType() {
		return transmissionType;
	}

	public void setTransmissionType(TransmissionType transmissionType) {
		this.transmissionType = transmissionType;
	}

	public ClassType getClassType() {
		return classType;
	}

	public void setClassType(ClassType classType) {
		this.classType = classType;
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

	public Discount getDiscount() {
		return discount;
	}

	public void setDiscount(Discount discount) {
		this.discount = discount;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}
