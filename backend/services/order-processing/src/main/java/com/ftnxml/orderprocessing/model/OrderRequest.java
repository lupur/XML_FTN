package com.ftnxml.orderprocessing.model;

import java.util.Date;

import com.ftnxml.orderprocessing.enums.OrderRequestStatus;

public class OrderRequest {
	private Long id;
	private OrderRequestStatus status;
	private Long userId;
	private Long ownerId;
	private Date createdOn;

	public OrderRequest() {
		super();
	}

	public OrderRequest(Long id, OrderRequestStatus status, Long userId, Long ownerId, Date createdOn) {
		super();
		this.id = id;
		this.status = status;
		this.userId = userId;
		this.ownerId = ownerId;
		this.createdOn = createdOn;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public OrderRequestStatus getStatus() {
		return status;
	}

	public void setStatus(OrderRequestStatus status) {
		this.status = status;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
}
