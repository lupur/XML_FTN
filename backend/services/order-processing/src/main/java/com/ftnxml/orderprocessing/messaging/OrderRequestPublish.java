package com.ftnxml.orderprocessing.messaging;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class OrderRequestPublish {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    private static final String ORDER_REQUEST_TOPIC = "orderrequest";

    public void sendOrderRequest(List<Long> vehicles) {
        try {
            kafkaTemplate.send(ORDER_REQUEST_TOPIC, objectMapper.writeValueAsString(vehicles));
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
