package com.ftnxml.soapservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.ftnxml.soapservice.client.UserClient;
import com.ftnxml.soapservice.dto.UserDto;
import com.ftnxml.soapservice.model.RegisterAgentRequest;
import com.ftnxml.soapservice.model.RegisterAgentResponse;

@Endpoint
public class UserEndpoint {

    private static final String NAMESPACE_URI = "www.soapservice.ftnxml.com/model/";

    @Autowired
    UserClient userClient;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "RegisterAgentRequest")
    @ResponsePayload
    public RegisterAgentResponse registerAgent(@RequestPayload RegisterAgentRequest request) {
        System.out.println("Into Agent Register SOAP request");
        if (request == null) {
            RegisterAgentResponse response = new RegisterAgentResponse();
            response.setId(-1);
            response.setStatus(400);
        }

        UserDto newAgent = new UserDto();
        newAgent.setUsername(request.getUsername());
        newAgent.setEmail(request.getEmail());
        newAgent.setPassword(request.getPassword());
        newAgent.setConfirmPassword(request.getConfirmPassword());

        System.out.println("New Agent Details: " + newAgent.toString());

        RegisterAgentResponse resp = userClient.registerAgent(newAgent);

        return resp;
    }
}