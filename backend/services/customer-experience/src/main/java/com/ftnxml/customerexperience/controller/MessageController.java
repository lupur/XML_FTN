package com.ftnxml.customerexperience.controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftnxml.customerexperience.client.UserDetailsClient;
import com.ftnxml.customerexperience.dto.MessageDto;
import com.ftnxml.customerexperience.dto.UserDto;
import com.ftnxml.customerexperience.model.Message;
import com.ftnxml.customerexperience.service.MessageService;;

@RestController
@RequestMapping("/message")
public class MessageController {
    @Autowired
    MessageService messageService;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    UserDetailsClient userDetailsClient;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getMessages() {
        List<MessageDto> messages = messageService.getAllMessages().stream()
                .map(message -> modelMapper.map(message, MessageDto.class)).collect(Collectors.toList());
        return ResponseEntity.ok(messages);
    }

    @GetMapping(value = "/authors/{authorId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getMessageByAuthorId(@PathVariable("authorId") Long authorId) {
        List<MessageDto> messages = messageService.getMessagesByAuthor(authorId).stream()
                .map(message -> modelMapper.map(message, MessageDto.class)).collect(Collectors.toList());
        return ResponseEntity.ok(messages);
    }

    @GetMapping(value = "/orders/{requestOrderId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getMessageByOrderId(@PathVariable("requestOrderId") Long requestOrderId) {
        List<MessageDto> messages = messageService.getMessagesByOrderId(requestOrderId).stream()
                .map(message -> modelMapper.map(message, MessageDto.class)).collect(Collectors.toList());
        return ResponseEntity.ok(messages);
    }

    @GetMapping(value = "/{messageId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getMessage(@PathVariable("messageId") Long messageId) {
        Message message = messageService.getMessage(messageId);
        if (message == null)
            ResponseEntity.notFound().build();
        MessageDto md = modelMapper.map(message, MessageDto.class);
        return ResponseEntity.ok(md);
    }

    @DeleteMapping(value = "/{messageId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity removeMessage(@PathVariable("messageId") Long messageId) {
        if (messageService.removeMessage(messageId))
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.notFound().build();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addMessage(@RequestHeader("Authorization") String token, @RequestBody MessageDto newMessage) {
        if (newMessage == null || newMessage.getContent().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        UserDto usr = userDetailsClient.getUserInfo(token);
        Message message = new Message();
        message.setAuthorId(usr.getId());
        message.setAuthorName(usr.getUsername());
        message.setContent(newMessage.getContent());
        message.setCreationDate(new Date());
        message.setOrderRequestId(newMessage.getOrderRequestId());

        if (messageService.addMessage(message))
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.badRequest().build();
    }
}
