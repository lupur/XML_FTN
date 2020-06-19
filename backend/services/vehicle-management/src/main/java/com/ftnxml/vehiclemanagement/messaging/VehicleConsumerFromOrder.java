package com.ftnxml.vehiclemanagement.messaging;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ftnxml.vehiclemanagement.dto.CreateRequestDto;
import com.ftnxml.vehiclemanagement.service.VehicleService;

@Service
public class VehicleConsumerFromOrder implements IVehicleMessaging {
	
	String NEW_ORDER_VEHICLE_RESPONSE_TOPIC = "new-order-request-vehicle-repsonse";
	
	@Autowired
	ObjectMapper objectMapper;
	
	@Autowired
	VehicleService vehicleService;
	
	@Autowired
	KafkaTemplate kafkaTemplate;

    @Override
    public void consumeOrderRequest(String content) {
    	try {
    		System.out.println("Received message from order-processing service: " + content);
        	Map<String, List<Integer>> map = objectMapper.readValue(content, Map.class);
        	Map.Entry<String,List<Integer>> requestVehicles = map.entrySet().iterator().next();
        	System.out.println(requestVehicles.getKey());
        	Long requestId = Long.valueOf(requestVehicles.getKey());
        	List<Integer> vehicleIDs = requestVehicles.getValue();
        	CreateRequestDto requestDto = new CreateRequestDto(requestId, vehicleIDs);
        	requestDto = vehicleService.proceedOrderRequest(requestDto);
        	System.out.println("Sending response to order-processing service");
        	sendVehicleResponse(requestDto);
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    
    public void sendVehicleResponse(CreateRequestDto requestDto) {
        try {
        	String content = objectMapper.writeValueAsString(requestDto);
        	System.out.println(content);
            kafkaTemplate.send(NEW_ORDER_VEHICLE_RESPONSE_TOPIC, content);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
