package com.ftnxml.soapservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.ftnxml.soapservice.client.VehicleClient;
import com.ftnxml.soapservice.model.AddBrandRequest;
import com.ftnxml.soapservice.model.AddBrandResponse;
import com.ftnxml.soapservice.model.AllBrandsRequest;
import com.ftnxml.soapservice.model.AllBrandsResponse;
import com.ftnxml.soapservice.model.Brand;
import com.ftnxml.soapservice.model.BrandByIdRequest;
import com.ftnxml.soapservice.model.BrandByIdResponse;

@Endpoint
public class BrandEndpoint {

    private static final String NAMESPACE_URI = "www.soapservice.ftnxml.com/model/";

    @Autowired
    VehicleClient vehicleClient;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "BrandByIdRequest")
    @ResponsePayload
    public BrandByIdResponse getBrand(@RequestPayload BrandByIdRequest request) {
        Brand b = vehicleClient.getBrand(request.getId());
        BrandByIdResponse response = new BrandByIdResponse();
        response.setBrand(b);

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "AllBrandsRequest")
    @ResponsePayload
    public AllBrandsResponse getAllBrands(@RequestPayload AllBrandsRequest request) {
        List<Brand> b = vehicleClient.getAllBrands();
        AllBrandsResponse response = new AllBrandsResponse();
        response.getBrands().addAll(b);
        for (Brand brand : b) {
            System.out.println("*********************");
            System.out.println("Size :  " + brand.getModel().size());
        }
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "AddBrandRequest")
    @ResponsePayload
    public AddBrandResponse getBrand(@RequestPayload AddBrandRequest request) {

        AddBrandResponse response = new AddBrandResponse();
        response.setResult(vehicleClient.addBrand(request.getName()));

        return response;
    }
}
