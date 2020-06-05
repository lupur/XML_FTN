package com.ftnxml.vehiclemanagement.dto;

import java.util.Set;

public class ImageDto {
	private String uri;
	private Set<VehicleDto> vehicles;

	public Set<VehicleDto> getVehicles() {
		return vehicles;
	}

	public void setVehicles(Set<VehicleDto> vehicles) {
		this.vehicles = vehicles;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}
}
