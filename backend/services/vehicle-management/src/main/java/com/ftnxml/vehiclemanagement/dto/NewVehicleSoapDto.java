package com.ftnxml.vehiclemanagement.dto;

public class NewVehicleSoapDto {
    private Long id;
    private String modelName;
    private String brandName;
    private String fuelTypeName;
    private String transmissionTypeName;
    private String location;
    private Double mileage;
    private Integer noOfSeats;
    private Long userId;
    private Double dailyPrice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getFuelTypeName() {
        return fuelTypeName;
    }

    public void setFuelTypeName(String fuelTypeName) {
        this.fuelTypeName = fuelTypeName;
    }

    public String getTransmissionTypeName() {
        return transmissionTypeName;
    }

    public void setTransmissionTypeName(String transmissionTypeName) {
        this.transmissionTypeName = transmissionTypeName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Double getMileage() {
        return mileage;
    }

    public void setMileage(Double mileage) {
        this.mileage = mileage;
    }

    public Integer getNoOfSeats() {
        return noOfSeats;
    }

    public void setNoOfSeats(Integer noOfSeats) {
        this.noOfSeats = noOfSeats;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Double getDailyPrice() {
        return dailyPrice;
    }

    public void setDailyPrice(Double dailyPrice) {
        this.dailyPrice = dailyPrice;
    }

    public boolean isValid() {
        boolean isValid = true;
        if (modelName == null || brandName == null || fuelTypeName == null || transmissionTypeName == null
                || location == null) {
            isValid = false;
        } else if (modelName.isEmpty() || brandName.isEmpty() || fuelTypeName.isEmpty()
                || transmissionTypeName.isEmpty() || location.isEmpty()) {
            isValid = false;
        }
        return isValid;
    }

    @Override
    public String toString() {
        return "NewVehicleDto [id=" + id + ", modelName=" + modelName + ", brandName=" + brandName + ", fuelTypeName="
                + fuelTypeName + ", transmissionTypeName=" + transmissionTypeName + ", location=" + location
                + ", mileage=" + mileage + ", NoOfSeats=" + noOfSeats + ", userId=" + userId + ", dailyPrice="
                + dailyPrice + "]";
    }

}