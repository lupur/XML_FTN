package com.ftnxml.vehiclemanagement.service;

import java.util.List;

import com.ftnxml.vehiclemanagement.model.TransmissionType;

public interface TransmissionTypeService {

    List<TransmissionType> getAllTransmissionTypes();

    TransmissionType getTransmissionType(Long id);

    boolean removeTransmissionType(Long id);

    boolean addTransmissionType(TransmissionType newTransmissionType);
}
