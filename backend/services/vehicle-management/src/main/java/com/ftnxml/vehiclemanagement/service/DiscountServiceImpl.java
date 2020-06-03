package com.ftnxml.vehiclemanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftnxml.vehiclemanagement.model.Discount;
import com.ftnxml.vehiclemanagement.repository.DiscountRepository;

@Service
public class DiscountServiceImpl implements DiscountService {

    @Autowired
    DiscountRepository discountRepository;

    @Override
    public List<Discount> getAllDiscounts() {
        return discountRepository.findAll();
    }

    @Override
    public Discount getDiscount(Long id) {
        try {
            Discount b = discountRepository.findById(id).get();
            return b;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean removeDiscount(Long id) {
        try {
            Discount b = discountRepository.findById(id).get();
            discountRepository.delete(b);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean addDiscount(Discount newDiscount) {
        if (getDiscount(newDiscount.getId()) != null) {
            return false;
        }

        discountRepository.save(newDiscount);
        return true;
    }

}
