package com.ftnxml.orderprocessing.dto;

public class ReportDto {

	private Long id;
	private Double distanceTraveled;
	
	public ReportDto() {
		
	}

	public ReportDto(Long id, Double distanceTraveled) {
		this.id = id;
		this.distanceTraveled = distanceTraveled;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getDistanceTraveled() {
		return distanceTraveled;
	}

	public void setDistanceTraveled(Double distanceTraveled) {
		this.distanceTraveled = distanceTraveled;
	}
	
}
