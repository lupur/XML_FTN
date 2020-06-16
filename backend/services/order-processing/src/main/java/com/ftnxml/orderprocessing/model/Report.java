package com.ftnxml.orderprocessing.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "order_report")
public class Report {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name= "report_id")
	private Long id;
	@OneToOne
	@JoinColumn(name ="order_id")
	private VehicleOrder vehicleOrder;
	@Column(name= "distance_traveled")
	private Double distanceTraveled;

	public Report() {
		super();
	}

	public Report(Long id, VehicleOrder vehicleOrder, Double distanceTraveled) {
		super();
		this.id = id;
		this.vehicleOrder = vehicleOrder;
		this.distanceTraveled = distanceTraveled;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public VehicleOrder getVehicleOrder() {
		return vehicleOrder;
	}

	public void setVehicleOrder(VehicleOrder vehicleOrder) {
		this.vehicleOrder = vehicleOrder;
	}

	public Double getDistanceTraveled() {
		return distanceTraveled;
	}

	public void setDistanceTraveled(Double distanceTraveled) {
		this.distanceTraveled = distanceTraveled;
	}

}
