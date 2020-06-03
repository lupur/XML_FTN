package com.ftnxml.vehiclemanagement.service;

import java.util.List;

import com.ftnxml.vehiclemanagement.model.PriceList;

public interface PriceListService {

    List<PriceList> getAllPriceLists();

    PriceList getPriceList(Long id);

    boolean removePriceList(Long id);

    boolean addPriceList(PriceList newPriceList);
}
