package com.ftnxml.orderprocessing.dto;

import java.util.Date;
import java.util.List;

public class VehiclesAvailabilityDto {
	
	private Date startDate;
	private Date endDate;
	private List<Long> vehicleIDs;
	
	VehiclesAvailabilityDto() {
		
	}
	
	VehiclesAvailabilityDto(Date startDate, Date endDate, List<Long> vehicleIDs) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.vehicleIDs = vehicleIDs;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public List<Long> getVehicleIDs() {
		return vehicleIDs;
	}

	public void setVehicleIDs(List<Long> vehicleIDs) {
		this.vehicleIDs = vehicleIDs;
	}

}
