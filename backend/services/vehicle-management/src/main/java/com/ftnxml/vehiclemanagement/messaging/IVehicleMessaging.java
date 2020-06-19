package com.ftnxml.vehiclemanagement.messaging;

import org.springframework.kafka.annotation.KafkaListener;

public interface IVehicleMessaging {

    String NEW_ORDER_REQUEST_TOPIC = "new-order-request";

    @KafkaListener(topics = NEW_ORDER_REQUEST_TOPIC)
    void consumeOrderRequest(String content);
}
