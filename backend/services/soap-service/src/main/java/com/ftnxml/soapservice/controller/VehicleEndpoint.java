package com.ftnxml.soapservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.ftnxml.soapservice.client.VehicleClient;
import com.ftnxml.soapservice.dto.NewVehicleSoapDto;
import com.ftnxml.soapservice.model.AddNewVehicleRequest;
import com.ftnxml.soapservice.model.AddNewVehicleResponse;

@Endpoint
public class VehicleEndpoint {

    private static final String NAMESPACE_URI = "www.soapservice.ftnxml.com/model/";

    @Autowired
    VehicleClient vehicleClient;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "AddNewVehicleRequest")
    @ResponsePayload
    public AddNewVehicleResponse addVehicle(@RequestPayload AddNewVehicleRequest request) {
        NewVehicleSoapDto newVehicle = new NewVehicleSoapDto();
        newVehicle.setBrandName(request.getBrandName());
        newVehicle.setDailyPrice(request.getDailyPrice());
        newVehicle.setFuelTypeName(request.getFuelTypeName());
        newVehicle.setLocation(request.getLocation());
        newVehicle.setMileage(request.getMileage());
        newVehicle.setModelName(request.getModelName());
        newVehicle.setNoOfSeats(request.getNoOfSeats());
        newVehicle.setTransmissionTypeName(request.getTransmissionTypeName());
        newVehicle.setUserId(request.getUserId());

        NewVehicleSoapDto response = vehicleClient.addVehicle(newVehicle);

        AddNewVehicleResponse res = new AddNewVehicleResponse();
        if (response == null) {
            res.setVehicleId(-1);
        } else {
            res.setVehicleId(response.getId());
        }

        return res;
    }

}