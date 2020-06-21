package com.ftnxml.soapservice.client;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
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

    public List<Brand> getAllBrands() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Brand>> response = restTemplate.exchange(BASE_URL + "brands/", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Brand>>() {
                });
        List<Brand> brands = response.getBody();

        return brands;
    }
}
