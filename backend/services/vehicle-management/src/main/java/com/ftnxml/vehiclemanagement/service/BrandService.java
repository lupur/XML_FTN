package com.ftnxml.vehiclemanagement.service;

import java.util.List;

import com.ftnxml.vehiclemanagement.model.Brand;

public interface BrandService {

    List<Brand> getAllBrands();

    Brand getBrand(Long id);

    boolean removeBrand(Long id);

    boolean addBrand(Brand newBrand);
}
