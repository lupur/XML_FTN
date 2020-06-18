package com.ftnxml.customerexperience.service;

import java.util.List;

import com.ftnxml.customerexperience.model.Message;

public interface MessageService {
	List<Message> getAllMessages();

    List<Message> getMessagesByAuthor(Long authorId);
    
    List<Message> getMessagesByOrderId(Long orderId);
    
    Message getMessage(Long id);

    boolean removeMessage(Long id);

    boolean addMessage(Message newMessage);
}
