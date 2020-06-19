package com.ftnxml.orderprocessing.messaging;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ftnxml.orderprocessing.dto.CreateRequestDto;

@Service
public class OrderRequestPublish implements IOrderMessaging {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    private static final String NEW_ORDER_REQUEST_TOPIC = "new-order-request";

    public void sendOrderRequest(Map<Long, List<Long>> requestVehicles) {
        try {
            kafkaTemplate.send(NEW_ORDER_REQUEST_TOPIC, objectMapper.writeValueAsString(requestVehicles));
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

	@Override
	public void consumeNewRequestVehicleResponse(String content) {
		System.out.println("Reseived response from vehicle service...");
		System.out.println(content);
		try {
			CreateRequestDto responseFromVehicle = objectMapper.readValue(content, CreateRequestDto.class);
			System.out.println(responseFromVehicle.getVehicles().get(1));
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("Response from vehicle could not be read");
		}
	}
}
