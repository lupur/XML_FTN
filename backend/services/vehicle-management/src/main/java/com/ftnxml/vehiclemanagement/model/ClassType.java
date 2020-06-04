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
@Table(name = "class_type")
public class ClassType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name= "class_type_id")
	private Long id;
	@Column(name= "class_type_name")
	private String name;
	@OneToMany(mappedBy="classType")
    private Set<Vehicle> vehicles;

	public ClassType() {
		super();
	}

	public ClassType(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Vehicle> getVehicles() {
		return vehicles;
	}

	public void setVehicles(Set<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}
	
}