package com.ftnxml.customerexperience.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ftnxml.customerexperience.model.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
	List<Message> findByAuthorId(Long authorId);
	
	List<Message> findByOrderRequestId(Long orderRequestId);
}