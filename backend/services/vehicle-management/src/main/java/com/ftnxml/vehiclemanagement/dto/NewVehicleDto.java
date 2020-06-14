package com.ftnxml.vehiclemanagement.dto;

public class NewVehicleDto {
    private Long id;
    private Long modelId;
    private Long pricelistId;
    private Long colDamageWaiverId;
    private Long discountId;
    private Long userId;
    private Long fuelTypeId;
    private Long transmissionTypeId;
    private Long classTypeId;
    private Double mileage;
    private Double mileageConstraint;
    private Boolean insurance;
    private Integer numberOfSeats;
    private Float rating;
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

    public Double getMileage() {
        return mileage;
    }

    public void setMileage(Double mileage) {
        this.mileage = mileage;
    }

    public Double getMileageConstraint() {
        return mileageConstraint;
    }

    public void setMileageConstraint(Double mileageConstraint) {
        this.mileageConstraint = mileageConstraint;
    }

    public Boolean isInsurance() {
        return insurance;
    }

    public void setInsurance(Boolean insurance) {
        this.insurance = insurance;
    }

    public Integer getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(Integer numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getInsurance() {
        return insurance;
    }

    @Override
    public String toString() {
        return "VehicleDto [modelId=" + modelId + ", pricelistId=" + pricelistId + ", colDamageWaiverId="
                + colDamageWaiverId + ", discountId=" + discountId + ", userId=" + userId + ", fuelTypeId=" + fuelTypeId
                + ", transmissionTypeId=" + transmissionTypeId + ", classTypeId=" + classTypeId + ", mileage=" + mileage
                + ", mileageConstraint=" + mileageConstraint + ", insurance=" + insurance + ", numberOfSeats="
                + numberOfSeats + ", rating=" + rating + ", location=" + location + "]";
    }

}