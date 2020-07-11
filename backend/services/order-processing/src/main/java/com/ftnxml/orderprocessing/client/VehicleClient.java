package com.ftnxml.orderprocessing.client;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ftnxml.orderprocessing.dto.SimpleVehicleDto;

@Service
public class VehicleClient {

    private final String BASE_URL = "http://vehicle-management:8082/";

    public SimpleVehicleDto getVehicle(Long vehicleId) {
        System.out.println("Path: " + BASE_URL + "simple/" + vehicleId);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<SimpleVehicleDto> response = restTemplate.exchange(BASE_URL + "simple/" + vehicleId,
                HttpMethod.GET, null, new ParameterizedTypeReference<SimpleVehicleDto>() {
                });
        return response.getBody();
    }
}
