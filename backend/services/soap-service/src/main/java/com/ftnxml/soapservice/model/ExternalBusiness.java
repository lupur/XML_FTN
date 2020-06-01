package com.ftnxml.soapservice.model;

import java.util.Date;

public class ExternalBusiness {
	private Long id;
	private String name;
	private boolean isRegistered;
	private Date registrationDate;

	public ExternalBusiness() {
		super();
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

	public boolean isRegistered() {
		return isRegistered;
	}

	public void setRegistered(boolean isRegistered) {
		this.isRegistered = isRegistered;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public ExternalBusiness(Long id, String name, boolean isRegistered, Date registrationDate) {
		super();
		this.id = id;
		this.name = name;
		this.isRegistered = isRegistered;
		this.registrationDate = registrationDate;
	}
}
