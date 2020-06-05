package com.ftnxml.vehiclemanagement.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "discount")
public class Discount {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name= "discount_id")
	private Long id;
	@Column(name= "number_of_days")
	private Integer numberOfDays;
	@Column(name= "percentage")
	private Integer percentage;
	@Column(name= "start_date")
	private Date startDate;
	@Column(name= "end_date")
	private Date endDate;
	@OneToMany(mappedBy="discount")
    private Set<Vehicle> vehicles;

	public Discount() {
		super();
	}

	public Discount(Long id, int numberOfDays, int percentage, Date startDate, Date endDate) {
		super();
		this.id = id;
		this.numberOfDays = numberOfDays;
		this.percentage = percentage;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getNumberOfDays() {
		return numberOfDays;
	}

	public void setNumberOfDays(Integer numberOfDays) {
		this.numberOfDays = numberOfDays;
	}

	public Integer getPercentage() {
		return percentage;
	}

	public void setPercentage(Integer percentage) {
		this.percentage = percentage;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Set<Vehicle> getVehicles() {
		return vehicles;
	}

	public void setVehicles(Set<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}
	
}
