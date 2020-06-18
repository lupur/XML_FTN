package com.ftnxml.customerexperience.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_messages")
public class Message {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "message_id")
	private Long id;
	@Column(name = "author_id")
	private Long authorId;
	@Column(name = "author_name")
	private String authorName;
	@Column(name = "order_request_id")
	private Long orderRequestId;
	@Column(name = "content")
	private String content;
	@Column(name = "creation_date")
	private Date creationDate;

	public Message() {
		super();
	}

	public Message(Long id, Long authorId, String authorName, Long orderRequestId, String content, Date creationDate) {
		this.id = id;
		this.authorId = authorId;
		this.authorName = authorName;
		this.orderRequestId = orderRequestId;
		this.content = content;
		this.creationDate = creationDate;
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

	public Long getOrderRequestId() {
		return orderRequestId;
	}

	public void setOrderRequestId(Long orderRequestId) {
		this.orderRequestId = orderRequestId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

}
