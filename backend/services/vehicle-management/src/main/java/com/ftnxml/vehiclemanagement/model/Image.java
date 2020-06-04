package com.ftnxml.vehiclemanagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "images")
public class Image {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name= "image_id")
	private Long id;
	@Column(name ="image_uri")
	private String uri;
    @ManyToOne
    @JoinColumn(name="vehicle_id")
	private Vehicle vehicle;

	public Image() {
		super();
	}

	public Image(Long id, String uri) {
		super();
		this.id = id;
		this.uri = uri;
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
