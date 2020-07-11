package com.ftnxml.soapservice.client;

import java.net.URL;

import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ftnxml.soapservice.dto.UserDto;
import com.ftnxml.soapservice.dto.UserDtoResponse;
import com.ftnxml.soapservice.model.RegisterAgentResponse;

@Service
public class UserClient {

    private final String BASE_URL = "http://user-management:8081/";

    public RegisterAgentResponse registerAgent(UserDto user) {
        try {
            System.out.println("Into register agent client");
            RestTemplate restTemplate = new RestTemplate();
            ObjectMapper mapper = new ObjectMapper();

            String json = mapper.writeValueAsString(user);
            System.out.println("JSON: " + json);

            System.out.println("Will create request");
            RequestEntity<String> requestEntity = RequestEntity.post(new URL(BASE_URL + "user/registerAgent").toURI())
                    .contentType(MediaType.APPLICATION_JSON).body(json);
            ResponseEntity<UserDtoResponse> response = restTemplate.exchange(requestEntity, UserDtoResponse.class);
            System.out.println("Register Agent Response: " + response.getBody().toString());
            RegisterAgentResponse resp = new RegisterAgentResponse();

            resp.setId(response.getBody().getId());
            resp.setStatus(response.getStatusCodeValue());
            System.out.println("ID : " + response.getBody() + " - Status: " + response.getStatusCodeValue());
            return resp;

        } catch (Exception e) {
            e.printStackTrace();
            RegisterAgentResponse resp = new RegisterAgentResponse();
            resp.setId(-1);
            resp.setStatus(400);
            return resp;
        }
    }
}