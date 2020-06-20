package com.ftnxml.vehiclemanagement.dto;

public class VehicleDto {
    private Long id;
    private BrandDto brand;

    public BrandDto getBrand() {
        return brand;
    }

    public void setBrand(BrandDto brand) {
        this.brand = brand;
    }

    private ModelDto model;
    private PriceListDto pricelist;
    private CollisionDamageWaiverDto colDamageWaiver;
    private DiscountDto discount;
    private Long userId;
    private FuelTypeDto fuelType;
    private TransmissionTypeDto transmissionType;
    private ClassTypeDto classType;
    private Double mileage;
    private Double mileageConstraint;
    private Boolean insurance;
    private Integer numberOfSeats;
    private Float rating;
    private String location;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ModelDto getModel() {
        return model;
    }

    public void setModel(ModelDto model) {
        this.model = model;
    }

    public PriceListDto getPricelist() {
        return pricelist;
    }

    public void setPricelist(PriceListDto pricelist) {
        this.pricelist = pricelist;
    }

    public CollisionDamageWaiverDto getColDamageWaiver() {
        return colDamageWaiver;
    }

    public void setColDamageWaiver(CollisionDamageWaiverDto colDamageWaiver) {
        this.colDamageWaiver = colDamageWaiver;
    }

    public DiscountDto getDiscount() {
        return discount;
    }

    public void setDiscount(DiscountDto discount) {
        this.discount = discount;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public FuelTypeDto getFuelType() {
        return fuelType;
    }

    public void setFuelType(FuelTypeDto fuelType) {
        this.fuelType = fuelType;
    }

    public TransmissionTypeDto getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(TransmissionTypeDto transmissionType) {
        this.transmissionType = transmissionType;
    }

    public ClassTypeDto getClassType() {
        return classType;
    }

    public void setClassType(ClassTypeDto classType) {
        this.classType = classType;
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

    public Boolean getInsurance() {
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

    @Override
    public String toString() {
        return "VehicleDto [id=" + id + ", brand=" + brand + ", model=" + model + ", pricelist=" + pricelist
                + ", colDamageWaiver=" + colDamageWaiver + ", discount=" + discount + ", userId=" + userId
                + ", fuelType=" + fuelType + ", transmissionType=" + transmissionType + ", classType=" + classType
                + ", mileage=" + mileage + ", mileageConstraint=" + mileageConstraint + ", insurance=" + insurance
                + ", numberOfSeats=" + numberOfSeats + ", rating=" + rating + ", location=" + location + "]";
    }

}