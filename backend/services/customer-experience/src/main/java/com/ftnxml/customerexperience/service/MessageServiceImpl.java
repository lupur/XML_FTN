package com.ftnxml.customerexperience.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftnxml.customerexperience.model.Message;
import com.ftnxml.customerexperience.repository.MessageRepository;

@Service
public class MessageServiceImpl implements MessageService {
	@Autowired
	MessageRepository messageRepository;
	
	@Override
	public List<Message> getAllMessages(){
		return messageRepository.findAll();
	}

	@Override
	public List<Message> getMessagesByAuthor(Long authorId){
		return messageRepository.findByAuthorId(authorId);
	}
    
	@Override
	public List<Message> getMessagesByOrderId(Long orderId){
		return messageRepository.findByOrderRequestId(orderId);
	}
    
	@Override
	public Message getMessage(Long id){
		try {
			Message m = messageRepository.findById(id).get();
			return m;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public boolean removeMessage(Long id){
		try {
			Message m = messageRepository.findById(id).get();
			messageRepository.delete(m);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean addMessage(Message newMessage){
		if (newMessage == null) {
			return false;
		}

		try {
			messageRepository.save(newMessage);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
