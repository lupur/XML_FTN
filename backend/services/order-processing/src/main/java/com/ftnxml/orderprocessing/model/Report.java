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
	private OrderRequest vehicleOrder;
	@Column(name= "distance_traveled")
	private int distanceTraveled;

	public Report() {
		super();
	}

	public Report(Long id, OrderRequest vehicleOrder, int distanceTraveled) {
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

	public OrderRequest getVehicleOrder() {
		return vehicleOrder;
	}

	public void setVehicleOrder(OrderRequest vehicleOrder) {
		this.vehicleOrder = vehicleOrder;
	}

	public int getDistanceTraveled() {
		return distanceTraveled;
	}

	public void setDistanceTraveled(int distanceTraveled) {
		this.distanceTraveled = distanceTraveled;
	}

}
