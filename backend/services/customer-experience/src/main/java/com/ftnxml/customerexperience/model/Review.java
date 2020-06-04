package com.ftnxml.customerexperience.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ftnxml.customerexperience.enums.ReviewStatus;

@Entity
@Table(name = "user_reviews")
public class Review {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long authorId;
	private String authorName;
	private Long vehicleOrderId;
	private String comment;
	private int rating;
	private Date creationDate;
	private ReviewStatus status;

	public Review() {
		super();
	}

	public Review(Long id, Long authorId, String authorName, Long vehicleOrderId, String comment, int rating,
	        Date creationDate, ReviewStatus status) {
		super();
		this.id = id;
		this.authorId = authorId;
		this.authorName = authorName;
		this.vehicleOrderId = vehicleOrderId;
		this.comment = comment;
		this.rating = rating;
		this.creationDate = creationDate;
		this.status = status;
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

	public ReviewStatus getStatus() {
		return status;
	}

	public void setStatus(ReviewStatus status) {
		this.status = status;
	}

}