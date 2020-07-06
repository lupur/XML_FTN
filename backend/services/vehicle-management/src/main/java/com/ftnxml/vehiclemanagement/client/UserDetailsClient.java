package com.ftnxml.vehiclemanagement.client;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ftnxml.vehiclemanagement.dto.UserDto;

@Service
public class UserDetailsClient {

    private final String BASE_URL = "http://user-management:8081";

    public UserDto getUserInfo(String token) {

        RestTemplate restTemplate = new RestTemplateBuilder(
                rt -> rt.getInterceptors().add((request, body, execution) -> {
                    request.getHeaders().add("Authorization", token);
                    return execution.execute(request, body);
                })).build();
        UserDto user = restTemplate.getForObject(BASE_URL + "/user/info", UserDto.class);
        return user;
    }

}
