package com.ftnxml.soapservice.client;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ftnxml.soapservice.model.Brand;

@Service
public class VehicleClient {

    private final String BASE_URL = "http://vehicle-management:8082/";

    public Brand getBrand(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        Brand b = restTemplate.getForObject(BASE_URL + "brands/{id}", Brand.class, id);
        return b;
    }
}
