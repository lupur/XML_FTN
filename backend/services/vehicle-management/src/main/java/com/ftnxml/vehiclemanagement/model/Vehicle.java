package com.ftnxml.vehiclemanagement.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "vehicle")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vehicle_id")
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "model_id")
    private Model model;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pricelist_id")
    private PriceList priceList;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "collision_damage_id")
    private CollisionDamageWaiver colDamageWaiver;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "discount_id")
    private Discount discount;
    @OneToMany(mappedBy = "vehicle")
    private Set<Image> images;
    @Column(name = "user_id")
    private Long userId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fuel_type_id")
    private FuelType fuelType;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "transmission_type_id")
    private TransmissionType transmissionType;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "class_type_id")
    private ClassType classType;
    @Column(name = "milleage")
    private Double mileage;
    @Column(name = "milleage_constraint")
    private Double mileageConstraint;
    @Column(name = "insurance")
    private Boolean insurance;
    @Column(name = "number_of_seats")
    private Integer numberOfSeats;
    @Column(name = "rating")
    private Float rating;
    @Column(name = "location")
    private String location;

    public Vehicle() {
        super();
    }

    public Vehicle(Long id, Model model, PriceList priceList, CollisionDamageWaiver colDamageWaiver, Set<Image> images,
            Long userId, FuelType fuelType, TransmissionType transmissionType, ClassType classType, Double mileage,
            Double mileageConstraint, Boolean insurance, Integer numberOfSeats, Float rating, Discount discount,
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
        this.location = location;
        this.discount = discount;
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

    public PriceList getPriceList() {
        return priceList;
    }

    public void setPriceList(PriceList priceList) {
        this.priceList = priceList;
    }

    public CollisionDamageWaiver getColDamageWaiver() {
        return colDamageWaiver;
    }

    public void setColDamageWaiver(CollisionDamageWaiver colDamageWaiver) {
        this.colDamageWaiver = colDamageWaiver;
    }

    public Set<Image> getImages() {
        return images;
    }

    public void setImages(Set<Image> images) {
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

    public boolean isInsurance() {
        return insurance;
    }

    public void setInsurance(boolean insurance) {
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

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

}
