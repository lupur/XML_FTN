package com.ftnxml.vehiclemanagement.service;

import java.util.List;

import com.ftnxml.vehiclemanagement.model.Discount;

public interface DiscountService {
    List<Discount> getAllDiscounts();

    Discount getDiscount(Long id);

    boolean removeDiscount(Long id);

    boolean addDiscount(Discount newDiscount);
}
