package com.ftnxml.vehiclemanagement.messaging;

import org.springframework.kafka.annotation.KafkaListener;

public interface IVehicleMessaging {

    String ORDER_REQUEST_TOPIC = "orderrequest";

    @KafkaListener(topics = ORDER_REQUEST_TOPIC)
    void consumeOrderRequest(String content);
}
