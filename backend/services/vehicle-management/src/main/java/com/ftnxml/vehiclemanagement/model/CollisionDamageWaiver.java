package com.ftnxml.vehiclemanagement.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "collision_damage")
public class CollisionDamageWaiver {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name= "collision_damage_id")
	private Long id;
	@Column(name= "collision_damage_price")
	private Double price;
	@OneToMany(mappedBy="colDamageWaiver")
    private Set<Vehicle> vehicles;

	public CollisionDamageWaiver() {
		super();
	}

	public CollisionDamageWaiver(Long id, Double price) {
		super();
		this.id = id;
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Set<Vehicle> getVehicles() {
		return vehicles;
	}

	public void setVehicles(Set<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}

}
