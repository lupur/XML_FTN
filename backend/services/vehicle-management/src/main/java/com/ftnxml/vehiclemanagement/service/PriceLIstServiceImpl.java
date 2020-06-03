package com.ftnxml.vehiclemanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftnxml.vehiclemanagement.model.PriceList;
import com.ftnxml.vehiclemanagement.repository.PriceListRepository;

@Service
public class PriceLIstServiceImpl implements PriceListService {

    @Autowired
    PriceListRepository priceListRepository;

    @Override
    public List<PriceList> getAllPriceLists() {
        return priceListRepository.findAll();
    }

    @Override
    public PriceList getPriceList(Long id) {
        try {
            PriceList b = priceListRepository.findById(id).get();
            return b;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean removePriceList(Long id) {
        try {
            PriceList b = priceListRepository.findById(id).get();
            priceListRepository.delete(b);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean addPriceList(PriceList newPriceList) {
        if (getPriceList(newPriceList.getId()) != null) {
            return false;
        }

        priceListRepository.save(newPriceList);
        return true;
    }

}
