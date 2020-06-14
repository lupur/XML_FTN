package com.ftnxml.soapservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.ftnxml.soapservice.client.VehicleClient;
import com.ftnxml.soapservice.model.Brand;
import com.ftnxml.soapservice.model.BrandDetailsRequest;
import com.ftnxml.soapservice.model.BrandDetailsResponse;

@Endpoint
public class BrandEndpoint {

    private static final String NAMESPACE_URI = "www.soapservice.ftnxml.com/model/";

    @Autowired
    VehicleClient vehicleClient;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "BrandDetailsRequest")
    @ResponsePayload
    public BrandDetailsResponse getBrand(@RequestPayload BrandDetailsRequest request) {
        Brand b = vehicleClient.getBrand(request.getId());
        BrandDetailsResponse response = new BrandDetailsResponse();
        response.setBrand(b);

        return response;
    }

}
