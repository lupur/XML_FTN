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
@Table(name = "brand")
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "brand_id")
    private Long id;
    @Column(name = "brand_name")
    private String name;
    @OneToMany(mappedBy="brand")
    private Set<Model> models;

    public Brand() {
        super();
    }

    public Brand(Long id, String name) {
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

	public Set<Model> getModels() {
		return models;
	}

	public void setModels(Set<Model> models) {
		this.models = models;
	}
   
}
