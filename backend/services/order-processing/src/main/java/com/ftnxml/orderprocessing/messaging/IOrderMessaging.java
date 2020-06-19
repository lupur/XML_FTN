package com.ftnxml.orderprocessing.messaging;

import org.springframework.kafka.annotation.KafkaListener;

public interface IOrderMessaging {
	
    String VEHICLE_REPSONSE_TOPIC = "new-order-request-vehicle-repsonse";

    @KafkaListener(topics = VEHICLE_REPSONSE_TOPIC)
    void consumeNewRequestVehicleResponse(String content);

}
