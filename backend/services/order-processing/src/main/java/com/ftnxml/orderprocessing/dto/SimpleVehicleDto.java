package com.ftnxml.orderprocessing.dto;

public class SimpleVehicleDto {
    private Long id;
    private String brand;
    private String model;
    private Long userId;
    private Float rating;
    private String location;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    @Override
    public String toString() {
        return "VehicleDto [id=" + id + ", brand=" + brand + ", model=" + model + ", userId=" + userId + ", rating="
                + rating + ", location=" + location + "]";
    }

}