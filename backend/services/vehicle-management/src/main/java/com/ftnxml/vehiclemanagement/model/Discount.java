package com.ftnxml.vehiclemanagement.model;

import java.util.Date;

public class Discount {
	private Long id;
	private int numberOfDays;
	private int percentage;
	private Date startDate;
	private Date endDate;

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

	public int getNumberOfDays() {
		return numberOfDays;
	}

	public void setNumberOfDays(int numberOfDays) {
		this.numberOfDays = numberOfDays;
	}

	public int getPercentage() {
		return percentage;
	}

	public void setPercentage(int percentage) {
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

}
