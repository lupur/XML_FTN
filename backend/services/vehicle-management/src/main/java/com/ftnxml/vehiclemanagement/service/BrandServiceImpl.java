package com.ftnxml.vehiclemanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftnxml.vehiclemanagement.model.Brand;
import com.ftnxml.vehiclemanagement.repository.BrandRepository;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    BrandRepository brandRepository;

    @Override
    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }

    @Override
    public Brand getBrand(Long id) {
        try {
            Brand b = brandRepository.findById(id).get();
            return b;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean removeBrand(Long id) {
        try {
            Brand b = brandRepository.findById(id).get();
            brandRepository.delete(b);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean addBrand(Brand newBrand) {
        if (getBrand(newBrand.getId()) != null) {
            return false;
        }

        brandRepository.save(newBrand);
        return true;
    }

}
