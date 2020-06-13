package com.ftnxml.vehiclemanagement.dto;

public class PriceListDto {
    private Long id;
    private double dailyPrice;
    private double mileagePenaltyPrice;

    public double getDailyPrice() {
        return dailyPrice;
    }

    public void setDailyPrice(double dailyPrice) {
        this.dailyPrice = dailyPrice;
    }

    public double getMileagePenaltyPrice() {
        return mileagePenaltyPrice;
    }

    public void setMileagePenaltyPrice(double mileagePenaltyPrice) {
        this.mileagePenaltyPrice = mileagePenaltyPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
