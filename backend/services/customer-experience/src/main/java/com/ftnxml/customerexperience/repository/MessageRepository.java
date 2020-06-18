package com.ftnxml.customerexperience.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ftnxml.customerexperience.model.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
	List<Message> findByAuthor_Id(Long authorId);
	
	List<Message> findAllByOrder_Request_Id(Long orderRequestId);
}