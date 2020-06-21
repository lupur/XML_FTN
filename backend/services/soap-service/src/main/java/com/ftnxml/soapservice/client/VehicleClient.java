package com.ftnxml.soapservice.client;

import java.net.URL;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ftnxml.soapservice.dto.NewBrandDto;
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

    public String addBrand(String name) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            NewBrandDto newBrand = new NewBrandDto();
            newBrand.name = name;
            ObjectMapper mapper = new ObjectMapper();

            String json = mapper.writeValueAsString(newBrand);

            RequestEntity<String> requestEntity = RequestEntity.post(new URL(BASE_URL + "brands/").toURI())
                    .contentType(MediaType.APPLICATION_JSON).body(json);
            ResponseEntity response = restTemplate.exchange(requestEntity, String.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                return "OK";
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            return "ERROR";
        }
        return "ERROR";
    }
}
