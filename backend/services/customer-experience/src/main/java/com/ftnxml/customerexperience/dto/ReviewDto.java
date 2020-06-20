package com.ftnxml.customerexperience.dto;

import java.util.Date;

public class ReviewDto {
	private Long id;
	private Long authorId;
	private Long vehicleId;
	private String authorName;
	private Long vehicleOrderId;
	private String comment;
	private int rating;
	private Date creationDate;
	
	public ReviewDto() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public Long getVehicleOrderId() {
		return vehicleOrderId;
	}

	public void setVehicleOrderId(Long vehicleOrderId) {
		this.vehicleOrderId = vehicleOrderId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Long getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(Long vehicleId) {
		this.vehicleId = vehicleId;
	}

}
