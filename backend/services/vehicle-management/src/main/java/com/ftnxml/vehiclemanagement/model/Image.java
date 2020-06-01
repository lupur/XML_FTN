package com.ftnxml.vehiclemanagement.model;

public class Image {
	private Long id;
	private String uri;
	private Vehicle vehicle;

	public Image() {
		super();
	}

	public Image(Long id, String uri, Vehicle vehicle) {
		super();
		this.id = id;
		this.uri = uri;
		this.vehicle = vehicle;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

}
