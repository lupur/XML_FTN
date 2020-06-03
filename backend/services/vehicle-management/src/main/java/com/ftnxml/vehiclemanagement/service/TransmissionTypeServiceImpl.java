package com.ftnxml.vehiclemanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ftnxml.vehiclemanagement.model.TransmissionType;
import com.ftnxml.vehiclemanagement.repository.TransmissionTypeRepository;

public class TransmissionTypeServiceImpl implements TransmissionTypeService {

    @Autowired
    TransmissionTypeRepository transmissionTypeRepository;

    @Override
    public List<TransmissionType> getAllTransmissionTypes() {
        return transmissionTypeRepository.findAll();
    }

    @Override
    public TransmissionType getTransmissionType(Long id) {
        try {
            TransmissionType b = transmissionTypeRepository.findById(id).get();
            return b;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean removeTransmissionType(Long id) {
        try {
            TransmissionType b = transmissionTypeRepository.findById(id).get();
            transmissionTypeRepository.delete(b);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean addTransmissionType(TransmissionType newTransmissionType) {
        if (getTransmissionType(newTransmissionType.getId()) != null) {
            return false;
        }

        transmissionTypeRepository.save(newTransmissionType);
        return true;
    }

}
