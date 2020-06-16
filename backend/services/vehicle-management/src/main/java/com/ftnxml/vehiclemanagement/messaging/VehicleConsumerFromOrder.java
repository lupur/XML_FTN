package com.ftnxml.vehiclemanagement.messaging;

import org.springframework.stereotype.Service;

@Service
public class VehicleConsumerFromOrder implements IVehicleMessaging {

    @Override
    public void consumeOrderRequest(String content) {
        System.out.println("Received message: " + content);
    }

}
